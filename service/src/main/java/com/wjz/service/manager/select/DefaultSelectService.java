package com.wjz.service.manager.select;

import java.io.Serializable;
import java.util.List;

import com.wjz.service.manager.assistant.MapperAssistant;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * <b>默认的查询操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public class DefaultSelectService<T> extends MapperAssistant<T> implements SelectService<T> {

	public DefaultSelectService(Mapper<T> mapper) {
		super(mapper);
	}

	@Override
	public T selectByPrimaryKey(Serializable primaryKey) {
		if (log.isDebugEnabled()) {
			log.debug("select a entity [" + getEntityClass() + "] the primaryKey is [" + primaryKey + "]");
		}
		return getMapper().selectByPrimaryKey(primaryKey);
	}

	@Override
	public List<T> selectList(T t) {
		return null;
	}

	@Override
	public List<T> selectByProperty(String property, Object value) {
		final Example example = new Example(getEntityClass());
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(property, value);
		if (log.isDebugEnabled()) {
			log.debug("select some of entity [" + getEntityClass() + "] the property is [" + property
					+ "], the value is [" + value + "]");
		}
		return getMapper().selectByExample(example);
	}

}
