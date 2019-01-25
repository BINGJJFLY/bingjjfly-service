package com.wjz.service.manager;

import com.wjz.service.manager.procedure.ProcedureServiceDelegate;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>数据库基础操作具体实现类</b>
 * 
 * @author iss002
 * @param <T>
 */
public abstract class AbstractManagerService<T> extends ProcedureServiceDelegate<T> {

	public AbstractManagerService(Mapper<T> mapper) {
		super(mapper);
	}

}
