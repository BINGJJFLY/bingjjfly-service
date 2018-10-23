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
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.core.annotation.AnnotationUtils;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.wjz.service.annotation.ViewObject;
import com.wjz.service.annotation.ViewProperty;
import com.wjz.service.exception.UnAssignableException;
import com.wjz.service.utils.DateUtils;

public class DefaultViewObjectFactory extends ViewObjectFactory {

	@Override
	public Object transform(Object domain) throws UnAssignableException {
		List<Object> list = null;
		Set<Object> set = null;
		if (domain != null) {
			Class<?> domainType = domain.getClass();
			MetaObject domainMetaObject = SystemMetaObject.forObject(domain);
			// 判断对象是否为集合对象
			boolean isCollection = domainMetaObject.isCollection();
			// 集合对象或Map对象
			if (isCollection || isMap(domainType)) {
				if (List.class.isAssignableFrom(domainType)) {
					Collection<?> dos = ((Collection<?>) domain);
					Iterator<?> iterator = dos.iterator();
					list = new ArrayList<>(((List<?>) domain).size());
					while (iterator.hasNext()) {
						list.add(transform(iterator.next()));
					}
					return list;
				} else if (Set.class.isAssignableFrom(domainType)) {
					Collection<?> dos = (Collection<?>) domain;
					Iterator<?> iterator = dos.iterator();
					set = new HashSet<>();
					while (iterator.hasNext()) {
						set.add(transform(iterator.next()));
					}
					return set;
				} else if (Map.class.isAssignableFrom(domainType)) {
					Map<?, ?> domap = (Map<?, ?>) domain;
					Map<Object, Object> map = new HashMap<>(domap.size());
					for(Map.Entry<?, ?> m : domap.entrySet()) {
						map.put(m.getKey(), transform(m.getValue()));
					}
					return map;
				}
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
									Object fieldValue = domainMetaObject.getValue(field.getName());
									// 声明字段类型不是集合类型
									if (!isCollection(field.getType())) {
										String name = null;
										String pattern = null;
										// 检查字段上是否有@ViewProperty注解
										ViewProperty viewPropertyAnno = AnnotationUtils.getAnnotation(field,
												ViewProperty.class);
										if (viewPropertyAnno != null) {
											name = viewPropertyAnno.name();
											pattern = viewPropertyAnno.pattern();
										}
										// 处理字段名称不匹配
										if (!StringUtils.isEmpty(name)) {
											fieldName = name;
										}
										// 处理日期类型，有默认格式
										if (Date.class.isAssignableFrom(field.getType())) {
											if (fieldValue != null) {
												if (StringUtils.isEmpty(pattern)) {
													pattern = DEFAULT_DATE_PATTERN;
												}
												fieldValue = DateUtils.format((Date) fieldValue, pattern);
											}
											setValue(fieldName, fieldValue, viewMetaObject);
										}
										// 处理数值类型，没有默认格式
										else if (Number.class.isAssignableFrom(field.getType())) {
											if (fieldValue != null) {
												if (!StringUtils.isEmpty(pattern)) {
													fieldValue = new DecimalFormat(pattern).format(fieldValue);
												} else {
													fieldValue = String.valueOf(fieldValue);
												}
											}
											setValue(fieldName, fieldValue, viewMetaObject);
										}
										// Map类型
										else if (Map.class.isAssignableFrom(field.getType())) {
											Map<?, ?> map = (Map<?, ?>) domainMetaObject.getValue(fieldName);
											setValue(fieldName, transform(map), viewMetaObject);
										}
										// 其他类型
										else {
											if (fieldValue != null) {
												fieldValue = String.valueOf(fieldValue);
											}
											setValue(fieldName, fieldValue, viewMetaObject);
										}
									}
									// 声明字段类型是集合类型
									else {
										Collection<?> collection = (Collection<?>) domainMetaObject.getValue(fieldName);
										setValue(fieldName, transform(collection), viewMetaObject);
									}
								}
							}
						}
						return viewObject;
					} else {
						throw new UnAssignableException("domain's viewObjectType must not be null.");
					}
				}
			}
		}
		return domain;
	}

	private void setValue(String name, Object value, MetaObject viewMetaObject) {
		if (value != null) {
			viewMetaObject.setValue(name, value);
		}
	}

}
