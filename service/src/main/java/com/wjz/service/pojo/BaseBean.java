package com.wjz.service.pojo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.wjz.service.utils.ReflectUtils;

/**
 * <b>基础bean的类</b>
 * <p>
 * {@code Field} 和 {@code Method} 使用了缓存
 * </p>
 * <p>
 * <ol>
 * <li>子类公共 {@code hashCode} 方法</li>
 * <li>子类公共 {@code equals} 方法</li>
 * <li>子类公共 {@code toString} 方法</li>
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

		try {
			Field[] fields = ReflectUtils.getLocalFields(clazz);
			if (!ArrayUtils.isEmpty(fields)) {
				for (Field field : fields) {
					if (!ReflectUtils.isStatic(field)) {
						ReflectionUtils.makeAccessible(field);
						Object fieldValue = ReflectionUtils.getField(field, this);
						result = prime * result + ((fieldValue == null) ? 0 : fieldValue.hashCode());
					}
				}
			}
		} catch (Exception e) {
			log.error("获得对象 [" + clazz.getName() + "] 的哈希值时异常", e);
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
		try {
			Method[] methods = ReflectUtils.getLocalMethods(clazz);
			if (!ArrayUtils.isEmpty(methods)) {
				for (Method method : methods) {
					if (!ReflectUtils.isStatic(method)) {
						String methodName = method.getName();
						if ((methodName.startsWith("get") && methodName.length() > 3)
								|| (methodName.startsWith("is") && methodName.length() > 2)) {
							ReflectionUtils.makeAccessible(method);
							Object thisVal = ReflectionUtils.invokeMethod(method, this);
							Object otherVal = ReflectionUtils.invokeMethod(method, other);
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
