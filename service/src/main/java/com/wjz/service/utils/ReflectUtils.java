package com.wjz.service.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;
import org.springframework.util.ReflectionUtils.MethodCallback;

/**
 * <b>反射工具类</b>
 * 
 * @author iss002
 *
 */
public abstract class ReflectUtils {

	/**
	 * 获得类型的泛型类型
	 * 
	 * @param targetClazz
	 * @return
	 */
	public static Type[] resolveActualGenericType(Class<?> targetClass) {
		Type genericType = targetClass.getGenericSuperclass();
		return ((ParameterizedType) genericType).getActualTypeArguments();
	}

	/**
	 * 获得目标类内的字段集合
	 * 
	 * @param clazz
	 *            目标类
	 * @return
	 */
	public static Field[] getLocalFields(Class<?> clazz) {
		final List<Field> fields = new ArrayList<Field>(16);
		ReflectionUtils.doWithLocalFields(clazz, new FieldCallback() {
			@Override
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				fields.add(field);
			}
		});
		return fields.toArray(new Field[fields.size()]);
	}

	/**
	 * 获得目标类内的方法集合
	 * 
	 * @param clazz
	 *            目标类
	 * @return
	 */
	public static Method[] getLocalMethods(Class<?> clazz) {
		final List<Method> methods = new ArrayList<Method>(32);
		ReflectionUtils.doWithLocalMethods(clazz, new MethodCallback() {
			@Override
			public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
				methods.add(method);
			}
		});
		return methods.toArray(new Method[methods.size()]);
	}

	/**
	 * 判断目标字段是否被 {@code static} 修饰
	 * 
	 * @param field
	 *            目标字段
	 * @return
	 */
	public static boolean isStatic(Field field) {
		int modifiers = field.getModifiers();
		if (Modifier.isStatic(modifiers)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断目标方法是否被 {@code static} 修饰
	 * 
	 * @param method
	 *            目标方法
	 * @return
	 */
	public static boolean isStatic(Method method) {
		int modifiers = method.getModifiers();
		if (Modifier.isStatic(modifiers)) {
			return true;
		}
		return false;
	}
}
