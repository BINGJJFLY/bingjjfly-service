package com.wjz.service.vo.handler;

import java.math.BigDecimal;

import org.apache.ibatis.reflection.MetaObject;

import com.wjz.service.anno.ViewProperty;

public class BigDecimalPropertyHandler extends NumberPropertyHandler<BigDecimal> {

	@Override
	protected void doHandle(Class<BigDecimal> fieldType, String fieldName, Object fieldValue, ViewProperty propertyAnno,
			MetaObject domainMetaObject, MetaObject viewMetaObject, Converter converter) {
		if (isAssignableFrom(fieldType)) {
			if (fieldValue != null) {
				setValue(fieldName, toString(fieldValue), viewMetaObject);
			}
		}
	}

	@Override
	protected String toString(Object fieldValue) {
		if (fieldValue != null) {
			return super.toString(((BigDecimal) fieldValue).doubleValue());
		}
		return super.toString(fieldValue);
	}

}
