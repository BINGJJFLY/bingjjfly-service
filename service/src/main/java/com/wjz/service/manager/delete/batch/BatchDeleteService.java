package com.wjz.service.manager.delete.batch;

import java.util.Collection;

import com.wjz.service.manager.ManagerException;
import com.wjz.service.manager.delete.DeleteService;

/**
 * <b>批量删除接口</b>
 * 
 * @author iss002
 * @param <T>
 */
public interface BatchDeleteService<T> extends DeleteService<T> {

	/**
	 * 批量删除一批数据
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
	void batchDeleteByPrimaryKey(Collection<T> collection) throws ManagerException;

	/**
	 * 批量删除一批数据
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
	void batchDeleteByPrimaryKey(Collection<T> collection, String sqlSessionFactoryBeanName) throws ManagerException;
}
