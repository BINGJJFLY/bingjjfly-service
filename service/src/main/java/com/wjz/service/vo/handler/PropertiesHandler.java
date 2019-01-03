package com.wjz.service.vo.handler;

import java.lang.reflect.Field;

import org.apache.ibatis.reflection.MetaObject;

import com.wjz.service.exception.UnAssignableException;

/**
 * <b>属性处理器</b>
 * <p>
 * 接口设计原因核心是要符合开放-封闭原则（程序扩展对外开放，修改对外封闭）
 * </p>
 * 
 * @author iss002
 *
 */
public interface PropertiesHandler {

	/**
	 * 对称加密密钥
	 */
	byte[] CIPHER_KEY = "AES_Cipher_Key:.".getBytes();

	/**
	 * 设置ViewObject对象字段值
	 * 
	 * @param field
	 * @param value
	 * @param viewMetaObject
	 */
	void setValue(String field, Object value, MetaObject viewMetaObject);

	/**
	 * 查询DomainObject对象字段值
	 * 
	 * @param field
	 * @param domainMetaObject
	 * @return
	 */
	Object getValue(String field, MetaObject domainMetaObject);

	/**
	 * DomainObject对象字段值处理转换为ViewObject对象字段值
	 * 
	 * @param field
	 * @param domainMetaObject
	 * @param viewMetaObject
	 * @param converter
	 * @throws UnAssignableException
	 */
	void handle(Field field, MetaObject domainMetaObject, MetaObject viewMetaObject, Converter converter)
			throws UnAssignableException;

}
