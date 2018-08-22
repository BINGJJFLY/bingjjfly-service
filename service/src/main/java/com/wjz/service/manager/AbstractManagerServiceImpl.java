package com.wjz.service.manager;

import java.io.Serializable;
import java.util.List;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * <b>抽象基础接口实现类</b>
 * <p>
 * 继承 {@link AbstractBatchManagerServiceImpl}，具备了批量操作的功能
 * <p>
 * 具体实现了操作数据的行为
 * 
 * @author iss002
 * @param <T>
 */
public abstract class AbstractManagerServiceImpl<T> extends AbstractBatchManagerServiceImpl<T> {

	private static final Logger log = LoggerFactory.getLogger(AbstractManagerServiceImpl.class);
	
	public AbstractManagerServiceImpl() {
		super();
	}

	public AbstractManagerServiceImpl(Mapper<T> mapper) {
		super(mapper);
	}

	public AbstractManagerServiceImpl(Mapper<T> mapper, SqlSessionFactoryBean sqlSessionFactory) {
		super(mapper);
		setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public Integer insertSelective(T t) throws ManagerException {
		hasMapper();
		log.info("try to insert a entity [" + getEntityClass() + "]/r/n" + t.toString());
		return getMapper().insertSelective(t);
	}

	@Override
	public Integer deleteByPrimaryKey(Serializable primaryKey) throws ManagerException {
		hasMapper();
		log.info("try to delete a entity [" + getEntityClass() + "] the primaryKey is [" + primaryKey + "]");
		return getMapper().deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(T t) throws ManagerException {
		hasMapper();
		log.info("try to update a entity [" + getEntityClass() + "]/r/n" + t.toString());
		return getMapper().updateByPrimaryKeySelective(t);
	}

	@Override
	public T selectByPrimaryKey(Serializable primaryKey) {
		hasMapper();
		log.debug("select a entity [" + getEntityClass() + "] the primaryKey is [" + primaryKey + "]");
		return getMapper().selectByPrimaryKey(primaryKey);
	}
	
	@Override
	public List<T> selectList(T t) {
		return null;
	}

	@Override
	public PageInfo<T> selectByPageInfo(T t, int pageNum, int pageSize) {
		hasMapper();
		log.debug("select some of entity [" + getEntityClass() + "] the pageNum is [" + pageNum + "] the pageSize is ["
				+ pageSize + "] the query model is \r\n" + t.toString());
		PageHelper.startPage(pageNum, pageSize);
		List<T> list = selectList(t);
		return new PageInfo<T>(list);
	}

	@Override
	public List<T> selectByProperty(String property, Object value) {
		hasMapper();
		final Example example = new Example(getEntityClass());
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(property, value);
		log.debug("select some of entity [" + getEntityClass() + "] the property is [" + property + "], the value is ["
				+ value + "]");
		return getMapper().selectByExample(example);
	}

	@Override
	public List<T> selectByProperty(String[] properties, Object[] values) {
		hasMapper();
		final StringBuilder loginfo = new StringBuilder();
		final Example example = new Example(getEntityClass());
		Criteria criteria = example.createCriteria();
		loginfo.append("select some of entity [" + getEntityClass() + "] ");
		for (int i = 0; i < properties.length; i++) {
			criteria.andEqualTo(properties[i], values[i]);
			loginfo.append("one of the property is [" + properties[i] + "], "
					+ "one of the value is [" + values[i] + "] and ");
		}
		log.debug(loginfo.toString());
		return getMapper().selectByExample(example);
	}

}
