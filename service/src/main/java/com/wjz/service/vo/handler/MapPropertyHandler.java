package com.wjz.service.vo.handler;

import java.util.Map;

import org.apache.ibatis.reflection.MetaObject;

import com.wjz.service.exception.UnAssignableException;

@SuppressWarnings("rawtypes")
public class MapPropertyHandler extends BasePropertyHandler<Map> {

	@Override
	protected void doHandle(Class<?> fieldType, String fieldName, Object fieldValue, String pattern,
			MetaObject domainMetaObject, MetaObject viewMetaObject, Transformer transformer)
			throws UnAssignableException {
		if (isAssignableFrom(fieldType)) {
			Map<?, ?> map = (Map<?, ?>) getValue(fieldName, domainMetaObject);
			setValue(fieldName, transformer.transforming(map), viewMetaObject);
		}
	}

}
