package com.wjz.service.manager.insert;

import com.wjz.service.manager.ManagerException;

/**
 * <b>基础插入接口</b>
 * 
 * @author iss002
 * @param <T>
 */
public interface InsertService<T> {

	/**
	 * 动态方式插入数据
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	Integer insertSelective(T t) throws ManagerException;
}
