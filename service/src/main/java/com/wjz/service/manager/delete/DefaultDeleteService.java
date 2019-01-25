package com.wjz.service.manager.delete;

import java.io.Serializable;

import com.wjz.service.manager.ManagerException;
import com.wjz.service.manager.assistant.BatchAssistant;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>默认的删除操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public class DefaultDeleteService<T> extends BatchAssistant<T> implements DeleteService<T> {

	public DefaultDeleteService(Mapper<T> mapper) {
		super(mapper);
	}

	@Override
	public Integer deleteByPrimaryKey(Serializable primaryKey) throws ManagerException {
		log.info("try to delete a entity [" + getEntityClass() + "] the primaryKey is [" + primaryKey + "]");
		return getMapper().deleteByPrimaryKey(primaryKey);
	}

}
