package com.wjz.service.manager;

import com.wjz.service.batch.BatchUpdateService;

/**
 * <b>基础更新接口</b>
 * 
 * @author iss002
 * @param <T>
 */
public interface UpdateService<T> extends BatchUpdateService<T> {

	Integer updateByPrimaryKeySelective(T t) throws ManagerException;
}
