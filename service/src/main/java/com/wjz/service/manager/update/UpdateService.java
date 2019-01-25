package com.wjz.service.manager.update;

import com.wjz.service.manager.ManagerException;

/**
 * <b>基础更新接口</b>
 * 
 * @author iss002
 * @param <T>
 */
public interface UpdateService<T> {

	/**
	 * 动态方式更新
	 * 
	 * @param t
	 * @return
	 * @throws ManagerException
	 */
	Integer updateByPrimaryKeySelective(T t) throws ManagerException;
}
