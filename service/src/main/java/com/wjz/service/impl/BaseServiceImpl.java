package com.wjz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisOperations;

import com.wjz.service.manager.procedure.BaseProcedureService;
import com.wjz.service.pojo.BaseBean;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>抽象业务接口实现</b>
 * <p>
 * 该类由各基础业务接口实现类继承如：
 * </p>
 * <p>
 * <em>PersonServiceImpl extends BaseServiceImpl implements PersonService</em>
 * </p>
 * <p>
 * 继承了该类的基础接口实现类都具备了 <em>操作数据库的功能</em> 和 <em>缓存功能</em>
 * </p>
 * <p>
 * 该类继承了 {@link AbstractManagerServiceImpl} 具备了操作数据库的功能
 * </p>
 * <p>
 * Redis缓存器 {@link RedisOperations}，注入 {@code RedisTemplate} 后默认使用
 * {@link RedisValueOperations}，支持复写
 * </p>
 * <p>
 * 通用日志属性 {@code log} 继承该类后不需要再创建日志类
 * </p>
 * 
 * @author iss002
 * @param <T>
 */
public abstract class BaseServiceImpl<T extends BaseBean<T>, M extends Mapper<T>> extends BaseProcedureService<T, M> {

	/**
	 * 通用日志
	 */
	protected final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 构造器注入 <tt>tk.Mappper</tt>
	 * 
	 * @param mapper
	 */
	public BaseServiceImpl(M mapper) {
		super(mapper);
	}

}
