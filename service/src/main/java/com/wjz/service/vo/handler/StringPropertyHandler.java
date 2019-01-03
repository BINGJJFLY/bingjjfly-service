package com.wjz.service.vo.handler;

import org.apache.ibatis.reflection.MetaObject;

import com.wjz.service.annotation.ViewProperty;

/**
 * <b>字符串类型字段处理器</b>
 * 
 * @author iss002
 *
 */
public class StringPropertyHandler extends BasePropertiesHandler<String> {

	@Override
	protected void doHandle(Class<String> fieldType, String fieldName, Object fieldValue, ViewProperty propertyAnno,
			MetaObject domainMetaObject, MetaObject viewMetaObject, Converter converter) {
		if (isAssignableFrom(fieldType)) {
			if (fieldValue != null) {
				setValue(fieldName, fieldValue, viewMetaObject);
			}
		}
	}

}
