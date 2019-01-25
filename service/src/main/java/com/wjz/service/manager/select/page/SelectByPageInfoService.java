package com.wjz.service.manager.select.page;

import com.github.pagehelper.PageInfo;
import com.wjz.service.manager.select.SelectService;

/**
 * <b>基于分页的基础查询接口</b>
 * 
 * @author iss002
 * @param <T>
 */
public interface SelectByPageInfoService<T> extends SelectService<T> {

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<T> selectByPageInfo(T t, int pageNum, int pageSize);
}
