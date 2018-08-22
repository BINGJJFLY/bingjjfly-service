package com.wjz.service.bussiness;

import java.io.Serializable;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.wjz.service.asserter.Asserter;
import com.wjz.service.asserter.DefaultAsserter;
import com.wjz.service.manager.AbstractManagerServiceImpl;
import com.wjz.service.redis.RedisOperations;
import com.wjz.service.redis.RedisValueOperations;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>抽象业务接口实现</b>
 * <p>
 * 该类由各基础业务接口实现类继承如：
 * <p>
 * <em>PersonServiceImpl extends AbstractBusinessServiceImpl<Person> implements PersonService</em>
 * <p>
 * 继承了该类的基础接口实现类都具备了 <em>操作数据库的功能</em> 和 <em>缓存功能</em> 和 <em>断言校验功能</em>
 * <p>
 * 该类继承了 {@link AbstractManagerServiceImpl} 具备了操作数据的功能
 * <p>
 * 断言器 {@link Asserter} ，构造器创建默认断言器、set注入可覆盖，拥有了断言行为又使用断言器进行断言校验
 * <p>
 * Redis缓存器 {@link RedisOperations}，注入 {@code RedisTemplate} 后默认使用
 * {@link RedisValueOperations}，支持复写
 * <p>
 * 通用日志属性 {@code log} 继承该类后不需要再创建日志类
 * 
 * @author iss002
 * @param <T>
 */
public abstract class AbstractBusinessServiceImpl<T extends Serializable> extends AbstractManagerServiceImpl<T> {

	/**
	 * 断言器，不可预测的方法入参时需要使用
	 */
	protected Asserter asserter = new DefaultAsserter();

	/**
	 * Redis缓存器
	 */
	protected RedisOperations<? extends Serializable, ?> redisOperations;
	
	/**
	 * 通用日志
	 */
	protected final Logger log = LoggerFactory.getLogger(getClass());

	public AbstractBusinessServiceImpl() {
		super();
	}
	
	public AbstractBusinessServiceImpl(Mapper<T> mapper) {
		super(mapper);
	}

	public AbstractBusinessServiceImpl(Mapper<T> mapper, SqlSessionFactoryBean sqlSessionFactory) {
		super(mapper, sqlSessionFactory);
	}

	@Override
	public void assertArgumentNotNull(Object arg, String message) {
		asserter.assertArgumentNotNull(arg, message);
	}

	@Override
	public void assertArgumentsNotNull(Object[] args, String message) {
		asserter.assertArgumentsNotNull(args, message);
	}

	public void setAsserter(Asserter asserter) {
		if (asserter instanceof Asserter) {
			this.asserter = asserter;
		}
	}

	@SuppressWarnings("unchecked")
	public void setRedisTemplate(RedisTemplate<? extends Serializable, ?> redisTemplate) {
		redisOperations = new RedisValueOperations((RedisTemplate<String, String>) redisTemplate);
	}
	
}
