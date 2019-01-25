package com.wjz.service.manager.insert;

import com.wjz.service.manager.ManagerException;
import com.wjz.service.manager.assistant.BatchAssistant;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>默认的插入操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public class DefaultInsertService<T> extends BatchAssistant<T> implements InsertService<T> {

	public DefaultInsertService(Mapper<T> mapper) {
		super(mapper);
	}

	@Override
	public Integer insertSelective(T t) throws ManagerException {
		log.info("try to insert a entity [" + getEntityClass() + "]\r\n" + t.toString());
		return getMapper().insertSelective(t);
	}

}
