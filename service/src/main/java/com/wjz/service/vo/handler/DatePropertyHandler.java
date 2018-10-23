package com.wjz.service.vo.handler;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.wjz.service.exception.UnAssignableException;
import com.wjz.service.utils.DateUtils;

public class DatePropertyHandler extends BasePropertyHandler<Date> {

	@Override
	protected void doHandle(Class<?> fieldType, String fieldName, Object fieldValue, String pattern,
			MetaObject domainMetaObject, MetaObject viewMetaObject, Transformer transformer)
			throws UnAssignableException {
		if (isAssignableFrom(fieldType)) {
			if (fieldValue != null) {
				if (StringUtils.isEmpty(pattern)) {
					pattern = DEFAULT_DATE_PATTERN;
				}
				fieldValue = DateUtils.format((Date) fieldValue, pattern);
			}
			setValue(fieldName, fieldValue, viewMetaObject);
		}
	}

}
