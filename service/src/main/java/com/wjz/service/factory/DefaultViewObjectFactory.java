package com.wjz.service.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
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

import com.wjz.service.annotation.ViewObject;
import com.wjz.service.exception.UnAssignableException;
import com.wjz.service.vo.handler.PropertyHandler;
import com.wjz.service.vo.handler.PropertyHandlerRegisty;
import com.wjz.service.vo.handler.Transformer;

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

	private final PropertyHandlerRegisty registy = new PropertyHandlerRegisty();

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
									try {
										PropertyHandler handler = registy.getHandler(field.getType());
										if (handler != null) {
											handler.handle(field, domainMetaObject, viewMetaObject, new Transformer() {
												@Override
												public Object transforming(Object target) throws UnAssignableException {
													return transform(target);
												}
											});
										}
									} catch (UnAssignableException e) {
										log.error("exception occurs when domain object [" + domainMetaObject.getOriginalObject()
										+ "] converting to view object.", e);
										throw e;
									}
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

	private boolean isAssignableFrom(Class<?> interfaceType, Class<?> classType) {
		if (interfaceType.isAssignableFrom(classType)) {
			return true;
		}
		return false;
	}

}
