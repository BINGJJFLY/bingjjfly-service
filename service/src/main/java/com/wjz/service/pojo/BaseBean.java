package com.wjz.service.pojo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javassist.Modifier;

/**
 * <b>基础bean的类</b>
 * <p>
 * 子类无需复写{@code hashCode}、{@code equals}、{@code toString}方法
 * </p>
 * <p>
 * <ol>
 * <li>公共 {@code hashCode}</li>
 * <li>公共 {@code equals}</li>
 * <li>公共 {@code toString}</li>
 * </ol>
 * </p>
 * 
 * @author iss002
 *
 * @param <T>
 *            基础bean
 */
@SuppressWarnings("serial")
public abstract class BaseBean<T> implements Serializable {

	private static final Logger log = LoggerFactory.getLogger(BaseBean.class);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		Class<?> clazz = getClass();
		Field[] fields = clazz.getDeclaredFields();
		if (!ArrayUtils.isEmpty(fields)) {
			try {
				for (Field field : fields) {
					if (!Modifier.isStatic(field.getModifiers())) {
						field.setAccessible(true);
						Object fieldValue = field.get(this);
						result = prime * result + ((fieldValue == null) ? 0 : fieldValue.hashCode());
					}
				}
			} catch (Exception e) {
				log.error("获得对象 [" + clazz.getName() + "] 的哈希值时异常", e);
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		Class<?> clazz = getClass();
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (clazz != obj.getClass()) {
			return false;
		}

		T other = (T) obj;
		Method[] methods = clazz.getDeclaredMethods();
		try {
			if (!ArrayUtils.isEmpty(methods)) {
				for (Method method : methods) {
					String methodName = method.getName();
					if ((methodName.startsWith("get") && methodName.length() > 3)
							|| (methodName.startsWith("is") && methodName.length() > 2)) {
						Object thisVal = method.invoke(this);
						Object otherVal = method.invoke(other);
						if (thisVal == null) {
							if (otherVal != null) {
								return false;
							}
						} else if (!thisVal.equals(otherVal)) {
							return false;
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("调用对象 [" + clazz.getName() + "] 的 [equals(Object obj)] 方法时异常", e);
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
