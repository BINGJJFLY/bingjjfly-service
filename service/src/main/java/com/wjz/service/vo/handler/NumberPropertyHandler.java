package com.wjz.service.vo.handler;

import java.text.DecimalFormat;

import org.apache.ibatis.reflection.MetaObject;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.wjz.service.exception.UnAssignableException;

public class NumberPropertyHandler extends BasePropertyHandler<Number> {

	@Override
	protected void doHandle(Class<?> fieldType, String fieldName, Object fieldValue, String pattern,
			MetaObject domainMetaObject, MetaObject viewMetaObject, Transformer transformer)
			throws UnAssignableException {
		if (isAssignableFrom(fieldType)) {
			if (fieldValue != null) {
				if (!StringUtils.isEmpty(pattern)) {
					fieldValue = new DecimalFormat(pattern).format(fieldValue);
				} else {
					fieldValue = String.valueOf(fieldValue);
				}
			}
			setValue(fieldName, fieldValue, viewMetaObject);
		}
	}

}
