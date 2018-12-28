package com.wjz.service.vo.manager;

import java.lang.reflect.Field;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

public class DefaultMetaObjectManager<T> implements MetaObjectManager<T> {
	
	private MetaObject metaObject;
	
	public DefaultMetaObjectManager(T t) {
		this.metaObject = SystemMetaObject.forObject(t);
	}
	
	@Override
	public MetaObject getMetaObject() {
		return metaObject;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T getOriginalObject() {
		return (T) metaObject.getOriginalObject();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getOriginalObjectType() {
		return (Class<T>) getOriginalObject().getClass();
	}

	@Override
	public Field[] getDeclaredFields() {
		return getOriginalObjectType().getDeclaredFields();
	}
	
}
