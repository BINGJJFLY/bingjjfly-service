package com.wjz.service.manager.procedure;

import com.wjz.service.manager.insert.batch.BaseBatchInsertService;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>存储过程操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public abstract class BaseProcedureService<T, M extends Mapper<T>> extends BaseBatchInsertService<T, M> {

	public BaseProcedureService(M mapper) {
		super(mapper);
	}

}
