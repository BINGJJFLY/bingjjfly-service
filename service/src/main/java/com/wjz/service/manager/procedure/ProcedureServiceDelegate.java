package com.wjz.service.manager.procedure;

import com.wjz.service.manager.insert.batch.BatchInsertServiceDelegate;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>存储过程操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public abstract class ProcedureServiceDelegate<T> extends BatchInsertServiceDelegate<T> {

	@SuppressWarnings("unused")
	private ProcedureService<T> delegate;
	
	public ProcedureServiceDelegate(Mapper<T> mapper) {
		super(mapper);
		delegate = new DefaultProcedureService<T>(mapper);
	}

	public void setDelegate(ProcedureService<T> delegate) {
		this.delegate = delegate;
	}

}
