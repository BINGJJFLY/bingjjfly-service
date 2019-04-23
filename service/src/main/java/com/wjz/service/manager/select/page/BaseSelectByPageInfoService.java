package com.wjz.service.manager.select.page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjz.service.manager.insert.BaseInsertService;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>分页查询操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public abstract class BaseSelectByPageInfoService<T, M extends Mapper<T>> extends BaseInsertService<T, M> {

	public BaseSelectByPageInfoService(M mapper) {
		super(mapper);
	}

	@Override
	public PageInfo<T> selectByPageInfo(T t, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		if (log.isDebugEnabled()) {
			log.debug("select some of entity [" + entityClass + "] the pageNum is [" + pageNum + "] the pageSize is ["
					+ pageSize + "] the query model is \r\n" + t.toString());
		}
		return new PageInfo<T>(selectList(t));
	}
}
