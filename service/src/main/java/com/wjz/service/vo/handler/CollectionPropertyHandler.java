package com.wjz.service.vo.handler;

import java.util.Collection;

import org.apache.ibatis.reflection.MetaObject;

import com.wjz.service.exception.UnAssignableException;

@SuppressWarnings("rawtypes")
public class CollectionPropertyHandler extends BasePropertyHandler<Collection> {

	@Override
	protected void doHandle(Class<?> fieldType, String fieldName, Object fieldValue, String pattern,
			MetaObject domainMetaObject, MetaObject viewMetaObject, Transformer transformer)
			throws UnAssignableException {
		if (isAssignableFrom(fieldType)) {
			Collection<?> collection = (Collection<?>) getValue(fieldName, domainMetaObject);
			setValue(fieldName, transformer.transforming(collection), viewMetaObject);
		}
	}

}
