package com.wjz.service.factory;

import java.util.Collection;
import java.util.Map;

import com.wjz.service.exception.UnAssignableException;

public abstract class ViewObjectFactory {
	
	protected static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public abstract Object transform(Object domain) throws UnAssignableException;
	
	public boolean isCollection(Class<?> type) {
		if (Collection.class.isAssignableFrom(type)) {
			return true;
		}
		return false;
	}
	
	public boolean isMap(Class<?> type) {
		if (Map.class.isAssignableFrom(type)) {
			return true;
		}
		return false;
	}
}
