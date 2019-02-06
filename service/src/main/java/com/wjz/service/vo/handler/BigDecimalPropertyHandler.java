package com.wjz.service.vo.handler;

import java.math.BigDecimal;

import org.apache.ibatis.reflection.MetaObject;

import com.wjz.service.anno.ViewProperty;

public class BigDecimalPropertyHandler extends BasePropertiesHandler<BigDecimal> {

	@Override
	protected void doHandle(Class<BigDecimal> fieldType, String fieldName, Object fieldValue, ViewProperty propertyAnno,
			MetaObject domainMetaObject, MetaObject viewMetaObject, Converter converter) {
		if (isAssignableFrom(fieldType)) {
			if (fieldValue != null) {
				setValue(fieldName, String.valueOf(((BigDecimal) fieldValue).doubleValue()), viewMetaObject);
			}
		}
	}

}
