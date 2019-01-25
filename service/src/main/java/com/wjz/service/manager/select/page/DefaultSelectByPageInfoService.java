package com.wjz.service.manager.select.page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjz.service.manager.select.DefaultSelectService;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>默认的分页查询操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public class DefaultSelectByPageInfoService<T> extends DefaultSelectService<T> implements SelectByPageInfoService<T> {

	public DefaultSelectByPageInfoService(Mapper<T> mapper) {
		super(mapper);
	}

	@Override
	public PageInfo<T> selectByPageInfo(T t, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		if (log.isDebugEnabled()) {
			log.debug("select some of entity [" + getEntityClass() + "] the pageNum is [" + pageNum
					+ "] the pageSize is [" + pageSize + "] the query model is \r\n" + t.toString());
		}
		return new PageInfo<T>(selectList(t));
	}

}
