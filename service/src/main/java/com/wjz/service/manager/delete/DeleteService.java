package com.wjz.service.manager.delete;

import java.io.Serializable;

import com.wjz.service.manager.ManagerException;

/**
 * <b>基础删除接口</b>
 * 
 * @author iss002
 * @param <T>
 */
public interface DeleteService<T> {

	/**
	 * 根据主键删除
	 * 
	 * @param primaryKey
	 * @return
	 * @throws ManagerException
	 */
	int deleteByPrimaryKey(Serializable primaryKey) throws ManagerException;
}
