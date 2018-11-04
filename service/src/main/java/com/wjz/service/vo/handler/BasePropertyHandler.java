package com.wjz.service.vo.handler;

import java.lang.reflect.Field;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.core.annotation.AnnotationUtils;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.wjz.service.annotation.ViewProperty;
import com.wjz.service.exception.UnAssignableException;

public abstract class BasePropertyHandler<T> extends CryptoPropertyHandler<T> {

	protected static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	@Override
	public void setValue(String field, Object value, MetaObject viewMetaObject) {
		if (value != null) {
			// 需要进行加密处理的字段
			if (crypto) {
				value = encrypt(value.toString().getBytes());
			}
			viewMetaObject.setValue(field, value);
		}
	}

	@Override
	public Object getValue(String field, MetaObject domainMetaObject) {
		return domainMetaObject.getValue(field);
	}

	protected boolean isAssignableFrom(Class<?> classType) {
		if (((Class<?>) getRawType()).isAssignableFrom(classType)) {
			return true;
		}
		return false;
	}

	@Override
	public void handle(Field field, MetaObject domainMetaObject, MetaObject viewMetaObject, Transformer transformer)
			throws UnAssignableException {
		try {
			String fieldName = field.getName();
			Object fieldValue = getValue(fieldName, domainMetaObject);
			Class<?> fieldType = field.getType();
			// 检查字段上是否有@ViewProperty注解
			ViewProperty propertyAnno = AnnotationUtils.getAnnotation(field, ViewProperty.class);
			if (propertyAnno != null) {
				String name = propertyAnno.name();
				// 处理字段名称不匹配
				if (!StringUtils.isEmpty(name)) {
					fieldName = name;
				}
			}

			doHandle(fieldType, fieldName, fieldValue, propertyAnno, domainMetaObject, viewMetaObject, transformer);
		} catch (Exception e) {
			throw new UnAssignableException(e);
		}
	}

	protected abstract void doHandle(Class<?> fieldType, String fieldName, Object fieldValue, ViewProperty propertyAnno,
			MetaObject domainMetaObject, MetaObject viewMetaObject, Transformer transformer) throws Exception;

}
