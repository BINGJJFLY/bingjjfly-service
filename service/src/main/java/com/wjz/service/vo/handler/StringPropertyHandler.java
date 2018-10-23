package com.wjz.service.vo.handler;

import org.apache.ibatis.reflection.MetaObject;

import com.wjz.service.exception.UnAssignableException;

public class StringPropertyHandler extends BasePropertyHandler<String> {

	@Override
	protected void doHandle(Class<?> fieldType, String fieldName, Object fieldValue, String pattern,
			MetaObject domainMetaObject, MetaObject viewMetaObject, Transformer transformer)
			throws UnAssignableException {
		setValue(fieldName, fieldValue, viewMetaObject);
	}

}
