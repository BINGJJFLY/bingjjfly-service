package com.wjz.service.vo.handler;

import org.apache.ibatis.reflection.MetaObject;

import com.wjz.service.anno.ViewProperty;

/**
 * <b>未知类型属性处理器</b>
 * <p>
 * 属性类型不存在指定属性处理器故不做处理，特殊处理扩展属性处理器即可
 * </p>
 * 
 * @author iss002
 *
 */
public class UnexpectedPropertyHandler extends BasePropertiesHandler<Object> {

	@Override
	protected void doHandle(Class<Object> fieldType, String fieldName, Object fieldValue, ViewProperty propertyAnno,
			MetaObject domainMetaObject, MetaObject viewMetaObject, Converter converter) {
		if (fieldValue != null) {
			setValue(fieldName, fieldValue, viewMetaObject);
		}
	}

}
