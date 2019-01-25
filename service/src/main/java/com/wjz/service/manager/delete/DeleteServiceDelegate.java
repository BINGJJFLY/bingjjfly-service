package com.wjz.service.manager.delete;

import java.io.Serializable;

import com.wjz.service.manager.ManagerException;
import com.wjz.service.manager.update.UpdateServiceDelegate;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>删除操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public abstract class DeleteServiceDelegate<T> extends UpdateServiceDelegate<T> {

	private DeleteService<T> delegate;

	public DeleteServiceDelegate(Mapper<T> mapper) {
		super(mapper);
		delegate = new DefaultDeleteService<T>(mapper);
	}

	@Override
	public Integer deleteByPrimaryKey(Serializable primaryKey) throws ManagerException {
		return delegate.deleteByPrimaryKey(primaryKey);
	}

	public void setDelegate(DeleteService<T> delegate) {
		this.delegate = delegate;
	}

}
