package com.wjz.service.vo.handler;

import java.lang.reflect.Field;
import org.apache.ibatis.reflection.MetaObject;
import com.wjz.service.exception.UnAssignableException;

/**
 * <b>属性处理器</b>
 * <p>
 * 接口设计原因核心是要符合开放-封闭原则（程序扩展对外开放，修改对外封闭）
 * </p>
 * <p>
 * 当有新的类型需要处理时不用修改 {@link com.wjz.service.factory.DefaultViewObjectFactory
 * DefaultViewObjectFactory} 的逻辑，而是添加属性处理器的具体实现类即可。
 * </p>
 * 
 * @author iss002
 *
 */
public interface PropertyHandler {

	void setValue(String field, Object value, MetaObject viewMetaObject);

	Object getValue(String field, MetaObject domainMetaObject);

	void handle(Field field, MetaObject domainMetaObject, MetaObject viewMetaObject, Transformer transformer)
			throws UnAssignableException;
}
