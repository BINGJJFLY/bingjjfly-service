package com.wjz.service.manager;

import java.io.Serializable;

import com.wjz.service.manager.batch.BatchDeleteService;

/**
 * <b>基础删除接口</b>
 * 
 * @author iss002
 * @param <T>
 */
public interface DeleteService<T> extends BatchDeleteService<T> {

	Integer deleteByPrimaryKey(Serializable primaryKey) throws ManagerException;
}
