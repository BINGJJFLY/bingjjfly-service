package com.wjz.service.vo.handler;

import java.util.Collection;
import org.apache.ibatis.reflection.MetaObject;
import com.wjz.service.annotation.ViewProperty;

@SuppressWarnings("rawtypes")
public class CollectionPropertyHandler extends BasePropertyHandler<Collection> {

	@Override
	protected void doHandle(Class<?> fieldType, String fieldName, Object fieldValue, ViewProperty propertyAnno,
			MetaObject domainMetaObject, MetaObject viewMetaObject, Transformer transformer) throws Exception {
		if (isAssignableFrom(fieldType)) {
			Collection<?> collection = (Collection<?>) getValue(fieldName, domainMetaObject);
			setValue(fieldName, transformer.transforming(collection), viewMetaObject);
		}
	}

}
