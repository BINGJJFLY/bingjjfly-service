package com.wjz.service.manager.insert;

import com.wjz.service.manager.ManagerException;
import com.wjz.service.manager.delete.BaseDeleteService;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>插入操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public abstract class BaseInsertService<T, M extends Mapper<T>> extends BaseDeleteService<T, M> {

	public BaseInsertService(M mapper) {
		super(mapper);
	}

	@Override
	public int insertSelective(T t) throws ManagerException {
		log.info("try to insert a entity [" + entityClass + "]\r\n" + t.toString());
		return mapper.insertSelective(t);
	}
}
