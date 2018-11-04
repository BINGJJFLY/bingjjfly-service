package com.wjz.service.vo.handler;

import java.text.DecimalFormat;
import org.apache.ibatis.reflection.MetaObject;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.wjz.service.annotation.ViewProperty;

public class NumberPropertyHandler extends BasePropertyHandler<Number> {

	@Override
	protected void doHandle(Class<?> fieldType, String fieldName, Object fieldValue, ViewProperty propertyAnno,
			MetaObject domainMetaObject, MetaObject viewMetaObject, Transformer transformer) throws Exception {
		if (isAssignableFrom(fieldType)) {
			if (fieldValue != null) {
				if (propertyAnno != null) {
					String pattern = propertyAnno.pattern();
					super.setCrypto(propertyAnno.crypto());
					if (!StringUtils.isEmpty(pattern)) {
						fieldValue = new DecimalFormat(pattern).format(fieldValue);
					}
				} else {
					fieldValue = String.valueOf(fieldValue);
				}
			}
			setValue(fieldName, fieldValue, viewMetaObject);
		}
	}

}
