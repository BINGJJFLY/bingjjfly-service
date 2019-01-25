package com.wjz.service.manager.update;

import com.wjz.service.manager.ManagerException;
import com.wjz.service.manager.assistant.BatchAssistant;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>默认的更新操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public class DefaultUpdateService<T> extends BatchAssistant<T> implements UpdateService<T> {

	public DefaultUpdateService(Mapper<T> mapper) {
		super(mapper);
	}

	@Override
	public Integer updateByPrimaryKeySelective(T t) throws ManagerException {
		log.info("try to update a entity [" + getEntityClass() + "]\r\n" + t.toString());
		return getMapper().updateByPrimaryKeySelective(t);
	}

}
