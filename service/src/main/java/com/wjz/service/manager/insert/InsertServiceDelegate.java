package com.wjz.service.manager.insert;

import com.wjz.service.manager.ManagerException;
import com.wjz.service.manager.delete.DeleteServiceDelegate;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>插入操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public abstract class InsertServiceDelegate<T> extends DeleteServiceDelegate<T> {

	private InsertService<T> delegate;

	public InsertServiceDelegate(Mapper<T> mapper) {
		super(mapper);
		delegate = new DefaultInsertService<T>(mapper);
	}

	@Override
	public int insertSelective(T t) throws ManagerException {
		return delegate.insertSelective(t);
	}

	public void setDelegate(InsertService<T> delegate) {
		this.delegate = delegate;
	}

}
