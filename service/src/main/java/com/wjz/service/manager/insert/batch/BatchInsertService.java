package com.wjz.service.manager.insert.batch;

import java.util.Collection;

import com.wjz.service.manager.ManagerException;
import com.wjz.service.manager.insert.InsertService;

/**
 * <b>批量插入接口</b>
 * 
 * @author iss002
 * @param <T>
 */
public interface BatchInsertService<T> extends InsertService<T> {

	/**
	 * 动态方式批量添加一批数据
	 * <p>
	 * 默认使用Mybatis的 {@code BatchExecutor} 执行器
	 * </p>
	 * <p>
	 * 如果是xml方式此方法需要被重写
	 * </p>
	 * 
	 * @param collection
	 * @throws ManagerException
	 */
	void batchInsertSelective(Collection<T> collection) throws ManagerException;

	/**
	 * 动态方式批量添加一批数据
	 * <p>
	 * 默认使用Mybatis的 {@code BatchExecutor} 执行器
	 * </p>
	 * <p>
	 * 如果是xml方式此方法不适用
	 * </p>
	 * 
	 * @param collection
	 * @param sqlSessionFactoryBeanName
	 * @throws ManagerException
	 */
	void batchInsertSelective(Collection<T> collection, String sqlSessionFactoryBeanName) throws ManagerException;
}
