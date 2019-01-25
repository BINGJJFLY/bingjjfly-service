package com.wjz.service.manager.update.batch;

import java.util.Collection;

import com.wjz.service.manager.ManagerException;
import com.wjz.service.manager.select.page.SelectByPageInfoServiceDelegate;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>批量更新操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public abstract class BatchUpdateServiceDelegate<T> extends SelectByPageInfoServiceDelegate<T> {

	private BatchUpdateService<T> delegate;

	public BatchUpdateServiceDelegate(Mapper<T> mapper) {
		super(mapper);
		delegate = new DefaultBatchUpdateService<T>(mapper);
	}

	@Override
	public void batchUpdateByPrimaryKeySelective(Collection<T> collection) throws ManagerException {
		delegate.batchUpdateByPrimaryKeySelective(collection);
	}

	@Override
	public void batchUpdateByPrimaryKeySelective(Collection<T> collection, String sqlSessionFactoryBeanName)
			throws ManagerException {
		delegate.batchUpdateByPrimaryKeySelective(collection, sqlSessionFactoryBeanName);
	}

	public void setDelegate(BatchUpdateService<T> delegate) {
		this.delegate = delegate;
	}
	
}
