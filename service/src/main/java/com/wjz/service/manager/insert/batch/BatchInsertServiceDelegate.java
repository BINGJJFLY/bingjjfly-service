package com.wjz.service.manager.insert.batch;

import java.util.Collection;

import com.wjz.service.manager.ManagerException;
import com.wjz.service.manager.delete.batch.BatchDeleteServiceDelegate;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>批量插入操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public abstract class BatchInsertServiceDelegate<T> extends BatchDeleteServiceDelegate<T> {

	private BatchInsertService<T> delegate;

	public BatchInsertServiceDelegate(Mapper<T> mapper) {
		super(mapper);
		delegate = new DefaultBatchInsertService<T>(mapper);
	}

	@Override
	public void batchInsertSelective(Collection<T> collection) throws ManagerException {
		delegate.batchInsertSelective(collection);
	}

	@Override
	public void batchInsertSelective(Collection<T> collection, String sqlSessionFactoryBeanName)
			throws ManagerException {
		delegate.batchInsertSelective(collection, sqlSessionFactoryBeanName);
	}

	public void setDelegate(BatchInsertService<T> delegate) {
		this.delegate = delegate;
	}

}
