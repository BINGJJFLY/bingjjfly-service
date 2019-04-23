package com.wjz.service.manager.delete;

import java.io.Serializable;

import com.wjz.service.manager.ManagerException;
import com.wjz.service.manager.update.BaseUpdateService;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>删除操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public abstract class BaseDeleteService<T, M extends Mapper<T>> extends BaseUpdateService<T, M> {

	public BaseDeleteService(M mapper) {
		super(mapper);
	}

	@Override
	public int deleteByPrimaryKey(Serializable primaryKey) throws ManagerException {
		log.info("try to delete a entity [" + entityClass + "] the primaryKey is [" + primaryKey + "]");
		return mapper.deleteByPrimaryKey(primaryKey);
	}
}
