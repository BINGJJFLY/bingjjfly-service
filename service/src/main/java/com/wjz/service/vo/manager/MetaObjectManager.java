package com.wjz.service.vo.manager;

import java.lang.reflect.Field;
import org.apache.ibatis.reflection.MetaObject;

public interface MetaObjectManager<T> {
	
	MetaObject getMetaObject();
	
	T getOriginalObject();

	Class<T> getOriginalObjectType();
	
	Field[] getDeclaredFields();
}
