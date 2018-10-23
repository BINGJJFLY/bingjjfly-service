package com.wjz.service.factory;

import com.wjz.service.exception.UnAssignableException;

/**
 * <b>Dao层对象转视图层对象工厂</b>
 * <p>
 * 功能：Dao层对象传入被转换为试图层对象
 * </p>
 * 
 * @author iss002
 *
 */
public abstract class ViewObjectFactory {

	protected static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public abstract Object transform(Object domain) throws UnAssignableException;

}
