package com.wjz.service.manager.procedure;

import com.wjz.service.manager.assistant.MapperAssistant;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>默认的存储过程操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public class DefaultProcedureService<T> extends MapperAssistant<T> implements ProcedureService<T> {

	public DefaultProcedureService(Mapper<T> mapper) {
		super(mapper);
	}

}
