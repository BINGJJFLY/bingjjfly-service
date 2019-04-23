package com.wjz.service.manager.select;

import java.io.Serializable;
import java.util.List;

import com.wjz.service.manager.BaseManagerService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * <b>查询操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public abstract class BaseSelectService<T, M extends Mapper<T>> extends BaseManagerService<T, M> {

	public BaseSelectService(M mapper) {
		super(mapper);
	}

	@Override
	public T selectByPrimaryKey(Serializable primaryKey) {
		if (log.isDebugEnabled()) {
			log.debug("select a entity [" + entityClass + "] the primaryKey is [" + primaryKey + "]");
		}
		return mapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public List<T> selectList(T t) {
		return null;
	}

	@Override
	public List<T> selectByProperty(String property, Object value) {
		final Example example = new Example(entityClass);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(property, value);
		if (log.isDebugEnabled()) {
			log.debug("select some of entity [" + entityClass + "] the property is [" + property + "], the value is ["
					+ value + "]");
		}
		return mapper.selectByExample(example);
	}
}
