package com.wjz.service.vo.handler;

import java.text.DecimalFormat;

import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.wjz.service.anno.ViewProperty;

/**
 * <b>数值类型处理器</b>
 * 
 * @author iss002
 *
 */
public class NumberPropertyHandler<T> extends BasePropertiesHandler<T> {

	private static final Logger log = LoggerFactory.getLogger(NumberPropertyHandler.class);

	@Override
	protected void doHandle(Class<T> fieldType, String fieldName, Object fieldValue, ViewProperty propertyAnno,
			MetaObject domainMetaObject, MetaObject viewMetaObject, Converter converter) {
		if (isAssignableFrom(fieldType)) {
			if (fieldValue != null) {
				try {
					if (propertyAnno != null) {
						fieldValue = format(propertyAnno.pattern(), fieldValue);
					} else {
						fieldValue = toString(fieldValue);
					}
				} catch (Exception e) {
					String error = "An exception occurs when numeric types are converted to string types. fieldName[{}], fieldValue[{}], domain[{}]";
					log.error(error, fieldName, fieldValue, domainMetaObject.getOriginalObject(), e);
					fieldValue = toString(fieldValue);
				}
				setValue(fieldName, fieldValue, viewMetaObject);
			}
		}
	}

	protected String format(String pattern, Object fieldValue) {
		if (fieldValue != null) {
			if (!StringUtils.isEmpty(pattern)) {
				return new DecimalFormat(pattern).format(fieldValue);
			}
		}
		return toString(fieldValue);
	}

	protected String toString(Object fieldValue) {
		if (fieldValue != null) {
			return String.valueOf(fieldValue);
		}
		return null;
	}

}
