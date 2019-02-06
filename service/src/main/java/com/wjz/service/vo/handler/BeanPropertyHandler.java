package com.wjz.service.vo.handler;

import org.apache.ibatis.reflection.MetaObject;

import com.wjz.service.anno.ViewProperty;
import com.wjz.service.vo.magician.SingleDO2VOMagician;

/**
 * <b>JavaBean属性处理器</b>
 * 
 * @author 123
 *
 */
public class BeanPropertyHandler extends BasePropertiesHandler<Object> {

	@Override
	protected void doHandle(Class<Object> fieldType, String fieldName, Object fieldValue, ViewProperty propertyAnno,
			MetaObject domainMetaObject, MetaObject viewMetaObject, Converter converter) {
		if (fieldValue != null) {
			if (propertyAnno == null || propertyAnno.convertible()) {
				fieldValue = converter.convert(getDO2VOMagician(), fieldValue);
			}
			setValue(fieldName, fieldValue, viewMetaObject);
		}
	}

}
