package com.wjz.service.vo.manager;

import java.lang.reflect.Field;

import org.apache.ibatis.reflection.MetaObject;

/**
 * <b>元对象管理者</b>
 * 
 * @author iss002
 *
 */
public interface MetaObjectManager {

	/**
	 * 获得元对象
	 * @return
	 */
	MetaObject getMetaObject();

	/**
	 * 获得原对象
	 * @return
	 */
	Object getOriginalObject();

	/**
	 * 获得原对象类型
	 * @return
	 */
	Class<?> getOriginalObjectType();

	/**
	 * 获得原对象声明字段集合
	 * @return
	 */
	Field[] getDeclaredFields();
}
