package com.wjz.service.manager.select.page;

import com.github.pagehelper.PageInfo;
import com.wjz.service.manager.insert.InsertServiceDelegate;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>分页查询操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public abstract class SelectByPageInfoServiceDelegate<T> extends InsertServiceDelegate<T> {

	private SelectByPageInfoService<T> delegate;

	public SelectByPageInfoServiceDelegate(Mapper<T> mapper) {
		super(mapper);
		delegate = new DefaultSelectByPageInfoService<T>(mapper);
	}

	@Override
	public PageInfo<T> selectByPageInfo(T t, int pageNum, int pageSize) {
		return delegate.selectByPageInfo(t, pageNum, pageSize);
	}

	public void setDelegate(SelectByPageInfoService<T> delegate) {
		this.delegate = delegate;
	}

}
