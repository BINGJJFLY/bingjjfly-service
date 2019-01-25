package com.wjz.service.manager.assistant;

import org.apache.ibatis.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b><code>tk.Mapper</code> 助手</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public abstract class MapperAssistant<T> extends TypeReference<T> {

	private Mapper<T> mapper;
	private final Class<T> entityClass;
	protected final Logger log = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("unchecked")
	public MapperAssistant(Mapper<T> mapper) {
		this.mapper = mapper;
		this.entityClass = (Class<T>) getRawType();
	}

	public Mapper<T> getMapper() {
		return mapper;
	}

	public void setMapper(Mapper<T> mapper) {
		this.mapper = mapper;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

}
