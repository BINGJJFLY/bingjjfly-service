package com.wjz.service.manager.select;

import java.io.Serializable;
import java.util.List;

import com.wjz.service.cache.AbstractCacheServiceImpl;
import com.wjz.service.manager.ManagerService;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>查询操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public abstract class SelectServiceDelegate<T> extends AbstractCacheServiceImpl implements ManagerService<T> {

	private SelectService<T> delegate;

	public SelectServiceDelegate(Mapper<T> mapper) {
		super();
		delegate = new DefaultSelectService<T>(mapper);
	}

	@Override
	public T selectByPrimaryKey(Serializable primaryKey) {
		return delegate.selectByPrimaryKey(primaryKey);
	}

	@Override
	public List<T> selectByProperty(String property, Object value) {
		return delegate.selectByProperty(property, value);
	}

	@Override
	public List<T> selectList(T t) {
		return delegate.selectList(t);
	}

	public void setDelegate(SelectService<T> delegate) {
		this.delegate = delegate;
	}

}
