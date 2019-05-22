package com.wjz.service.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wjz.service.utils.ReflectUtils;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>数据库基础操作具体实现类</b>
 * 
 * @author iss002
 * @param <T>
 */
public abstract class BaseManagerService<T, M extends Mapper<T>> implements ManagerService<T> {

	protected M mapper;
	protected final Class<T> entityClass;
	protected final Logger log = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("unchecked")
	public BaseManagerService(M mapper) {
		this.mapper = mapper;
		this.entityClass = (Class<T>) ReflectUtils.getSuperclassTypeParameter(getClass());
	}

	public void setMapper(M mapper) {
		this.mapper = mapper;
	}

}
