package com.wjz.service.vo.handler;

import java.lang.reflect.Field;
import org.apache.ibatis.reflection.MetaObject;
import com.wjz.service.exception.UnAssignableException;

public interface PropertyHandler {

	void setValue(String field, Object value, MetaObject viewMetaObject);

	Object getValue(String field, MetaObject domainMetaObject);

	void handle(Field field, MetaObject domainMetaObject, MetaObject viewMetaObject, Transformer transformer) throws UnAssignableException;
}
