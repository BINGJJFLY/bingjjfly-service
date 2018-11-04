package com.wjz.service.vo.handler;

import org.apache.ibatis.reflection.MetaObject;
import com.wjz.service.annotation.ViewProperty;

/**
 * <b>布尔类型属性处理器</b>
 * 
 * @author iss002
 *
 */
public class BooleanPropertyHandler extends BasePropertyHandler<Boolean> {

	@Override
	protected void doHandle(Class<?> fieldType, String fieldName, Object fieldValue, ViewProperty propertyAnno,
			MetaObject domainMetaObject, MetaObject viewMetaObject, Transformer transformer) throws Exception {
		if (isAssignableFrom(fieldType)) {
			setValue(fieldName, fieldValue, viewMetaObject);
		}
	}

}
