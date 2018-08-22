package com.wjz.service.manager;

import com.wjz.service.batch.BatchInsertService;

/**
 * <b>基础插入接口</b>
 * 
 * @author iss002
 * @param <T>
 */
public interface InsertService<T> extends BatchInsertService<T> {

	/**
	 * 插入一条数据
	 * <p>
	 * 没有注入 {@code tk.mybatis.mapper.common.Mapper<T>} 时此方法需要被重写
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	Integer insertSelective(T t) throws ManagerException;
}
