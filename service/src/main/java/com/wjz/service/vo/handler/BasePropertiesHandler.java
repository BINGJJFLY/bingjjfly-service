package com.wjz.service.vo.handler;

import java.lang.reflect.Field;

import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;

import com.wjz.service.anno.ViewProperty;
import com.wjz.service.context.ApplicationContextHolder;
import com.wjz.service.exception.UnAssignableException;
import com.wjz.service.vo.magician.DO2VOMagician;
import com.wjz.service.vo.magician.SingleDO2VOMagician;

/**
 * <b>基础属性处理器</b>
 * 
 * @author iss002
 *
 * @param <T>
 */
public abstract class BasePropertiesHandler<T> extends CryptoPropertiesHandler<T> {

	private static final Logger log = LoggerFactory.getLogger(BasePropertiesHandler.class);

	protected static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	@Override
	public void setValue(String field, Object value, MetaObject viewMetaObject) {
		if (value != null) {
			// 需要进行加密处理的属性
			if (crypto) {
				value = encrypt(value.toString().getBytes());
			}
			viewMetaObject.setValue(field, value);
		}
	}

	@Override
	public Object getValue(String field, MetaObject domainMetaObject) {
		return domainMetaObject.getValue(field);
	}

	protected boolean isAssignableFrom(Class<?> classType) {
		if (((Class<?>) getRawType()).isAssignableFrom(classType)) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handle(Field field, MetaObject domainMetaObject, MetaObject viewMetaObject, Converter converter)
			throws UnAssignableException {
		String fieldName = null;
		Object fieldValue = null;
		boolean working = true;
		try {
			fieldName = field.getName();
			fieldValue = getValue(fieldName, domainMetaObject);
			Class<T> fieldType = (Class<T>) field.getType();
			ViewProperty propertyAnno = AnnotationUtils.getAnnotation(field, ViewProperty.class);
			if (propertyAnno != null) {
				String name = propertyAnno.name();
				boolean crypto = propertyAnno.crypto();
				// 属性名称不匹配处理
				if (!StringUtils.isEmpty(name)) {
					fieldName = name;
				}
				// 属性加密处理
				setCrypto(crypto);
				// 是否进行转换
				working = propertyAnno.working();
			}

			if (working) {
				doHandle(fieldType, fieldName, fieldValue, propertyAnno, domainMetaObject, viewMetaObject, converter);
			}
		} catch (Exception e) {
			log.error("An exception occurs when handling property. fieldName[{}], fieldValue[{}], domain[{}]",
					fieldName, fieldValue, domainMetaObject.getOriginalObject(), e);
			throw new UnAssignableException(e);
		}
	}

	public DO2VOMagician getDO2VOMagician() {
		DO2VOMagician magician = null;
		try {
			magician = (DO2VOMagician) ApplicationContextHolder.getBean(DO2VOMagician.class);
		} catch (NoUniqueBeanDefinitionException e) {
			log.error("The bean type of DO2VOMagician not unique.");
			// try to find DO2VOMagician by name and type
			magician = (DO2VOMagician) ApplicationContextHolder.getBean("singleDO2VOMagician", DO2VOMagician.class);
		} catch (NoSuchBeanDefinitionException e) {
			log.error("There is no bean type of DO2VOMagician.");
		} catch (Exception e) {
			log.error("An exception occurs when getting DO2VOMagician.", e);
		}
		if (magician == null) {
			magician = new SingleDO2VOMagician(null);
		}
		return magician;
	}

	protected abstract void doHandle(Class<T> fieldType, String fieldName, Object fieldValue, ViewProperty propertyAnno,
			MetaObject domainMetaObject, MetaObject viewMetaObject, Converter converter);

}
