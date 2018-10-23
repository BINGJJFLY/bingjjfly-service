package com.wjz.service.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.core.annotation.AnnotationUtils;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.wjz.service.annotation.ViewObject;
import com.wjz.service.annotation.ViewProperty;
import com.wjz.service.exception.UnAssignableException;
import com.wjz.service.utils.DateUtils;

/**
 * <b>默认Dao层对象转视图层对象工厂</b>
 * <p>
 * 支持Dao层对象类型、Dao层对象集合类型、Dao层对象Map类型
 * </p>
 * 
 * @author iss002
 *
 */
public class DefaultViewObjectFactory extends ViewObjectFactory {

	private static final Logger log = LoggerFactory.getLogger(DefaultViewObjectFactory.class);

	@Override
	public Object transform(Object domain) throws UnAssignableException {
		List<Object> list = null;
		Set<Object> set = null;
		if (domain != null) {
			Class<?> domainType = domain.getClass();
			MetaObject domainMetaObject = SystemMetaObject.forObject(domain);
			// 集合对象
			if (isAssignableFrom(Collection.class, domainType)) {
				Collection<?> dos = ((Collection<?>) domain);
				Iterator<?> iterator = dos.iterator();
				if (isAssignableFrom(List.class, domainType)) {
					list = new ArrayList<>(((List<?>) domain).size());
					while (iterator.hasNext()) {
						list.add(transform(iterator.next()));
					}
					return list;
				} else if (isAssignableFrom(Set.class, domainType)) {
					set = new HashSet<>();
					while (iterator.hasNext()) {
						set.add(transform(iterator.next()));
					}
					return set;
				}
			}
			// Map对象
			else if (isAssignableFrom(Map.class, domainType)) {
				Map<?, ?> domap = (Map<?, ?>) domain;
				Map<Object, Object> map = new HashMap<>(domap.size());
				for (Map.Entry<?, ?> m : domap.entrySet()) {
					map.put(m.getKey(), transform(m.getValue()));
				}
				return map;
			}
			// 普通Bean对象
			else {
				ViewObject viewObjectAnno = AnnotationUtils.getAnnotation(domainType, ViewObject.class);
				if (viewObjectAnno != null) {
					Type viewType = viewObjectAnno.type();
					if (viewType != null) {
						Object viewObject = ReflectUtils.newInstance((Class<?>) viewType);
						MetaObject viewMetaObject = SystemMetaObject.forObject(viewObject);
						Field[] fields = domainMetaObject.getOriginalObject().getClass().getDeclaredFields();
						if (!ArrayUtils.isEmpty(fields)) {
							for (Field field : fields) {
								// 排除静态字段
								if (!Modifier.isStatic(field.getModifiers())) {
									String fieldName = field.getName();
									Object fieldValue = getValue(field.getName(), domainMetaObject);
									Class<?> fieldType = field.getType();
									String name = null;
									String pattern = null;
									// 检查字段上是否有@ViewProperty注解
									ViewProperty propertyAnno = AnnotationUtils.getAnnotation(field,
											ViewProperty.class);
									if (propertyAnno != null) {
										name = propertyAnno.name();
										pattern = propertyAnno.pattern();
									}
									// 处理字段名称不匹配
									if (!StringUtils.isEmpty(name)) {
										fieldName = name;
									}
									handle(fieldType, fieldName, fieldValue, pattern, domainMetaObject, viewMetaObject);
								}
							}
						}
						return viewObject;
					}
				}
			}
		}
		return domain;
	}

	private void handle(Class<?> fieldType, String fieldName, Object fieldValue, String pattern,
			MetaObject domainMetaObject, MetaObject viewMetaObject) {
		try {
			// 处理日期类型，有默认格式
			if (isAssignableFrom(Date.class, fieldType)) {
				if (fieldValue != null) {
					if (StringUtils.isEmpty(pattern)) {
						pattern = DEFAULT_DATE_PATTERN;
					}
					fieldValue = DateUtils.format((Date) fieldValue, pattern);
				}
				setValue(fieldName, fieldValue, viewMetaObject);
			}
			// 处理数值类型，没有默认格式
			else if (isAssignableFrom(Number.class, fieldType)) {
				if (fieldValue != null) {
					if (!StringUtils.isEmpty(pattern)) {
						fieldValue = new DecimalFormat(pattern).format(fieldValue);
					} else {
						fieldValue = String.valueOf(fieldValue);
					}
				}
				setValue(fieldName, fieldValue, viewMetaObject);
			}
			// 声明字段类型是集合类型
			else if (isAssignableFrom(Collection.class, fieldType)) {
				Collection<?> collection = (Collection<?>) domainMetaObject.getValue(fieldName);
				setValue(fieldName, transform(collection), viewMetaObject);
			}
			// 处理Map类型
			else if (isAssignableFrom(Map.class, fieldType)) {
				Map<?, ?> map = (Map<?, ?>) domainMetaObject.getValue(fieldName);
				setValue(fieldName, transform(map), viewMetaObject);
			}
			// 处理其他类型
			else {
				if (fieldValue != null) {
					fieldValue = String.valueOf(fieldValue);
				}
				setValue(fieldName, fieldValue, viewMetaObject);
			}
		} catch (Exception e) {
			log.error("exception occurs when domain object [" + domainMetaObject.getOriginalObject()
					+ "] converting to view object.", e);
			throw new UnAssignableException();
		}
	}

	private Object getValue(String field, MetaObject domainMetaObject) {
		return domainMetaObject.getValue(field);
	}

	private void setValue(String field, Object value, MetaObject viewMetaObject) {
		if (value != null) {
			viewMetaObject.setValue(field, value);
		}
	}

	private boolean isAssignableFrom(Class<?> interfaceType, Class<?> classType) {
		if (interfaceType.isAssignableFrom(classType)) {
			return true;
		}
		return false;
	}

}
