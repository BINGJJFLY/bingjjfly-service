package com.wjz.service.manager;

import com.github.pagehelper.PageInfo;

/**
 * <b>基于分页的基础查询接口</b>
 * 
 * @author iss002
 * @param <T>
 */
public interface SelectByPageInfoService<T> {

	PageInfo<T> selectByPageInfo(T t, int pageNum, int pageSize);
}
