package com.wjz.service.batch;

import java.util.Collection;

import com.wjz.service.manager.ManagerException;

/**
 * <b>批量更新接口</b>
 * 
 * @author iss002
 * @param <T>
 */
public interface BatchUpdateService<T> {

	/**
	 * 批量更新一批数据
	 * <p>
	 * 默认使用Mybatis的 {@code BatchExecutor} 执行器
	 * <p>
	 * 如果是xml方式此方法需要被重写
	 * @param collection
	 * @throws ManagerException
	 */
	void batchUpdateByPrimaryKeySelective(Collection<T> collection) throws ManagerException;
	
	/**
	 * 批量更新一批数据
	 * <p>
	 * 默认使用Mybatis的 {@code BatchExecutor} 执行器
	 * <p>
	 * 如果是xml方式此方法不适用
	 * @param collection
	 * @param sqlSessionFactoryBeanName SqlSessionFactoryBean的id属性值
	 * @throws ManagerException
	 */
	void batchUpdateByPrimaryKeySelective(Collection<T> collection, String sqlSessionFactoryBeanName) throws ManagerException;
	
}
