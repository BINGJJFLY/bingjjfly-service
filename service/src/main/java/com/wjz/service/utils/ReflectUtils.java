package com.wjz.service.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.wjz.service.exception.UnAssignableException;

/**
 * <b>反射工具类</b>
 * 
 * @author iss002
 *
 */
public abstract class ReflectUtils {

	/**
	 * 获得类型的泛型类型
	 * @param targetClazz
	 * @return
	 */
	public static Type[] resolveActualGenericType(Class<?> targetClass) {
		Type genericType = targetClass.getGenericSuperclass();
		return ((ParameterizedType) genericType).getActualTypeArguments();
	}

	public static <V> List<V> transferDO2VO(Object d, Class<V> v) throws UnAssignableException {
		return null;
	}
}
