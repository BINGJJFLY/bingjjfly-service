package com.wjz.service.vo.handler;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.wjz.service.cache.Cache;

/**
 * <b>属性处理器注册机</b>
 * 
 * @author iss002
 *
 */
public final class PropertyHandlerRegisty {

	private final Cache<Type, PropertyHandler> TYPE_HANDLER_MAP = new Cache<Type, PropertyHandler>();

	public PropertyHandlerRegisty() {
		registy(Date.class, new DatePropertyHandler());
		registy(Number.class, new NumberPropertyHandler());
		registy(String.class, new StringPropertyHandler());
		registy(Boolean.class, new BooleanPropertyHandler());
		registy(Collection.class, new CollectionPropertyHandler());
		registy(Map.class, new MapPropertyHandler());
		registy(void.class, new UnexpectedPropertyHandler());
	}

	public void registy(Type type, PropertyHandler handler) {
		TYPE_HANDLER_MAP.put(type, handler);
	}
	
	public PropertyHandler getHandler(Type type) {
		if (Number.class.isAssignableFrom((Class<?>) type)) {
			type = Number.class;
		} else if (Collection.class.isAssignableFrom((Class<?>) type)) {
			type = Collection.class;
		} else if (Map.class.isAssignableFrom((Class<?>) type)) {
			type = Map.class;
		}
		PropertyHandler handler = TYPE_HANDLER_MAP.get(type);
		if (handler == null) {
			handler = unexpected();
		}
		return handler;
	}
	
	public PropertyHandler unexpected() {
		return TYPE_HANDLER_MAP.get(void.class);
	}

}
