package com.wjz.service.manager.delete.batch;

import java.util.Collection;

import com.wjz.service.manager.ManagerException;
import com.wjz.service.manager.update.batch.BatchUpdateServiceDelegate;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>批量删除操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public abstract class BatchDeleteServiceDelegate<T> extends BatchUpdateServiceDelegate<T> {

	private BatchDeleteService<T> delegate;
	
	public BatchDeleteServiceDelegate(Mapper<T> mapper) {
		super(mapper);
		delegate = new DefaultBatchDeleteService<T>(mapper);
	}
	
	@Override
	public void batchDeleteByPrimaryKey(Collection<T> collection) throws ManagerException {
		delegate.batchDeleteByPrimaryKey(collection);
	}
	
	@Override
	public void batchDeleteByPrimaryKey(Collection<T> collection, String sqlSessionFactoryBeanName)
			throws ManagerException {
		delegate.batchDeleteByPrimaryKey(collection, sqlSessionFactoryBeanName);
	}

	public void setDelegate(BatchDeleteService<T> delegate) {
		this.delegate = delegate;
	}
	
}
