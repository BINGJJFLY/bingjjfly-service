package com.wjz.service.vo.handler;

import java.util.Date;
import org.apache.ibatis.reflection.MetaObject;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.wjz.service.annotation.ViewProperty;
import com.wjz.service.utils.DateUtils;

public class DatePropertyHandler extends BasePropertyHandler<Date> {

	@Override
	protected void doHandle(Class<?> fieldType, String fieldName, Object fieldValue, ViewProperty propertyAnno,
			MetaObject domainMetaObject, MetaObject viewMetaObject, Transformer transformer) throws Exception {
		if (isAssignableFrom(fieldType)) {
			if (fieldValue != null) {
				String pattern = DEFAULT_DATE_PATTERN;
				if (propertyAnno != null) {
					if (!StringUtils.isEmpty(pattern)) {
						pattern = propertyAnno.pattern();
					}
				}
				fieldValue = DateUtils.format((Date) fieldValue, pattern);
			}
			setValue(fieldName, fieldValue, viewMetaObject);
		}
	}

}
