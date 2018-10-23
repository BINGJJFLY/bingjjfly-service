package com.wjz.service.vo.handler;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class PropertyHandlerRegisty {

	private final Map<Type, PropertyHandler> TYPE_HANDLER_MAP = new ConcurrentHashMap<Type, PropertyHandler>();

	public PropertyHandlerRegisty() {
		registy(Date.class, new DatePropertyHandler());
		registy(Number.class, new NumberPropertyHandler());
		registy(String.class, new StringPropertyHandler());
		registy(Collection.class, new CollectionPropertyHandler());
		registy(Map.class, new MapPropertyHandler());
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
		return TYPE_HANDLER_MAP.get(type);
	}

}
