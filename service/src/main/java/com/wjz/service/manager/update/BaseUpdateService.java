package com.wjz.service.manager.update;

import com.wjz.service.manager.ManagerException;
import com.wjz.service.manager.select.BaseSelectService;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>更新操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public abstract class BaseUpdateService<T, M extends Mapper<T>> extends BaseSelectService<T, M> {

	public BaseUpdateService(M mapper) {
		super(mapper);
	}

	@Override
	public int updateByPrimaryKeySelective(T t) throws ManagerException {
		log.info("try to update a entity [" + entityClass + "]\r\n" + t.toString());
		return mapper.updateByPrimaryKeySelective(t);
	}
}
