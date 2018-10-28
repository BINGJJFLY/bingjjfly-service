package com.wjz.service.vo.handler;

import java.util.Map;
import org.apache.ibatis.reflection.MetaObject;
import com.wjz.service.annotation.ViewProperty;

@SuppressWarnings("rawtypes")
public class MapPropertyHandler extends BasePropertyHandler<Map> {

	@Override
	protected void doHandle(Class<?> fieldType, String fieldName, Object fieldValue, ViewProperty propertyAnno,
			MetaObject domainMetaObject, MetaObject viewMetaObject, Transformer transformer) throws Exception {
		if (isAssignableFrom(fieldType)) {
			Map<?, ?> map = (Map<?, ?>) getValue(fieldName, domainMetaObject);
			setValue(fieldName, transformer.transforming(map), viewMetaObject);
		}
	}

}
