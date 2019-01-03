package com.wjz.service.vo.manager;

import java.lang.reflect.Field;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

/**
 * <b>默认的元对象管理者</b>
 * 
 * @author iss002
 *
 */
public class DefaultMetaObjectManager implements MetaObjectManager {

	/**
	 * 元对象
	 * <p>
	 * 通过反射查询、设置对象属性值
	 * </p>
	 */
	private MetaObject metaObject;

	public DefaultMetaObjectManager(Object t) {
		this.metaObject = SystemMetaObject.forObject(t);
	}

	@Override
	public MetaObject getMetaObject() {
		return metaObject;
	}

	@Override
	public Object getOriginalObject() {
		return metaObject.getOriginalObject();
	}

	@Override
	public Class<?> getOriginalObjectType() {
		return getOriginalObject().getClass();
	}

	@Override
	public Field[] getDeclaredFields() {
		return getOriginalObjectType().getDeclaredFields();
	}

}
