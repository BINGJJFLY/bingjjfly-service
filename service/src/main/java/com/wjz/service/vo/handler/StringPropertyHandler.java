package com.wjz.service.vo.handler;

import org.apache.ibatis.reflection.MetaObject;
import com.wjz.service.annotation.ViewProperty;

public class StringPropertyHandler extends BasePropertyHandler<String> {

	@Override
	protected void doHandle(Class<?> fieldType, String fieldName, Object fieldValue, ViewProperty propertyAnno,
			MetaObject domainMetaObject, MetaObject viewMetaObject, Transformer transformer) throws Exception {
		if (isAssignableFrom(fieldType)) {
			setValue(fieldName, fieldValue, viewMetaObject);
		}
	}

}
