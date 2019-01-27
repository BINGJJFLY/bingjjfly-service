package com.wjz.service.manager.update;

import com.wjz.service.manager.ManagerException;
import com.wjz.service.manager.select.SelectServiceDelegate;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>更新操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public abstract class UpdateServiceDelegate<T> extends SelectServiceDelegate<T> {

	private UpdateService<T> delegate;

	public UpdateServiceDelegate(Mapper<T> mapper) {
		super(mapper);
		delegate = new DefaultUpdateService<T>(mapper);
	}

	@Override
	public int updateByPrimaryKeySelective(T t) throws ManagerException {
		return delegate.updateByPrimaryKeySelective(t);
	}

	public void setDelegate(UpdateService<T> delegate) {
		this.delegate = delegate;
	}

}
