package com.wjz.service.manager;

import java.util.Collection;
import java.util.Iterator;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wjz.service.cache.AbstractCacheServiceImpl;
import com.wjz.service.context.ApplicationContext;
import com.wjz.service.exception.UnAssignableException;
import com.wjz.service.utils.ReflectUtils;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>抽象的批量处理基础实现类</b>
 * <p>
 * 实现了 {@link ManagerService}，具体实现了批量操作数据库的行为
 * <p>
 * {@link SqlSessionFactoryBean} 用于创建 {@link SqlSession}，支持set注入、默认值、方法内指定值
 * <p>
 * 核心属性 {@link Mapper} 用于操作数据库（建议注入），支持构造器注入、set注入，同时支持不使用
 * {@code tk.mybatis.mapper.common.Mapper<T>} API的方式，但是有关的方法需要被重写
 * <p>
 * Mybatis 的 {@code namespace} 命名空间主要用于
 * {@code session.insert(namespace, object)} 等
 * 
 * @author iss002
 * @param <T>
 */
public abstract class AbstractBatchManagerServiceImpl<T> extends AbstractCacheServiceImpl implements ManagerService<T> {

	private static final Logger log = LoggerFactory.getLogger(AbstractBatchManagerServiceImpl.class);

	private SqlSessionFactoryBean sqlSessionFactory;

	private Mapper<T> mapper;

	private Class<?> mapperClass;

	private Class<T> entityClass;

	private String namespace;

	private static final String INSERT_METHOD_NAME = ".insertSelective";

	private static final String UPDATE_METHOD_NAME = ".updateByPrimaryKeySelective";

	private static final String DELETE_METHOD_NAME = ".deleteByPrimaryKey";

	private static final String DEFAULT_SQLSESSIONFACTORY_NAME = "sqlSessionFactory";

	@SuppressWarnings("unchecked")
	public void init() {
		this.entityClass = (Class<T>) ReflectUtils.resolveActualGenericType(getClass())[0];
	}

	public AbstractBatchManagerServiceImpl() {
		init();
	}

	public AbstractBatchManagerServiceImpl(Mapper<T> mapper) {
		init();
		if (!Mapper.class.isAssignableFrom(mapper.getClass())) {
			String e = "the argument 'mapper' [" + mapper.getClass().getName()
					+ "] is not the subinterface of tk.mybatis.mapper.common.Mapper";
			throw new UnAssignableException(e);
		}
		this.mapper = mapper;
		this.mapperClass = mapper.getClass();
		this.namespace = mapperClass.getName();
	}

	private SqlSession createSqlSession() throws Exception {
		return sqlSessionFactory.getObject().openSession(ExecutorType.BATCH, false);
	}

	private void commit(SqlSession sqlSession) {
		if (sqlSession != null) {
			sqlSession.commit();
			sqlSession.clearCache();
		}
	}

	private void rollback(SqlSession sqlSession) {
		if (sqlSession != null) {
			sqlSession.rollback();
		}
	}

	private void close(SqlSession sqlSession) {
		if (sqlSession != null) {
			sqlSession.close();
		}
	}

	public boolean hasMapperAndEntity() {
		return getMapper() != null && getMapperClass() != null && getEntityClass() != null;
	}

	public Mapper<T> hasMapper() {
		if (!hasMapperAndEntity()) {
			return null;
		}
		return getMapper();
	}

	@Override
	public void batchInsertSelective(Collection<T> collection) throws ManagerException {
		if (!hasSqlSessionFactory()) {
			return;
		}

		batchInsertSelective(collection, sqlSessionFactory, namespace);
	}

	public void batchInsertSelective(Collection<T> collection, SqlSessionFactoryBean sqlSessionFactory,
			String namespace) throws ManagerException {
		if (!hasSqlSessionFactory()) {
			return;
		}

		SqlSession sqlSession = null;
		try {
			if (collection != null && !collection.isEmpty()) {
				sqlSession = createSqlSession();
				Iterator<T> iterator = collection.iterator();
				while (iterator.hasNext()) {
					T t = iterator.next();
					if (t != null) {
						log.info("【insert a new entity [" + entityClass.getName() + "]】\r\n" + t.toString());
						sqlSession.insert(namespace + INSERT_METHOD_NAME, t);
					}
				}
				commit(sqlSession);
			}
		} catch (Exception e) {
			log.error("【Unexpected exception when try to insert some of entity [" + entityClass.getName()
					+ "] by batch type】", e);
			rollback(sqlSession);
			throw new ManagerException(e);
		} finally {
			close(sqlSession);
		}
	}

	@Override
	public void batchInsertSelective(Collection<T> collection, String sqlSessionFactoryBeanName)
			throws ManagerException {
		if (!hasSqlSessionFactory(sqlSessionFactoryBeanName)) {
			return;
		}

		batchInsertSelective(collection);
	}

	@Override
	public void batchUpdateByPrimaryKeySelective(Collection<T> collection) throws ManagerException {
		if (!hasSqlSessionFactory()) {
			return;
		}

		batchUpdateByPrimaryKeySelective(collection, sqlSessionFactory, namespace);
	}

	public void batchUpdateByPrimaryKeySelective(Collection<T> collection, SqlSessionFactoryBean sqlSessionFactory,
			String namespace) throws ManagerException {
		if (!hasSqlSessionFactory()) {
			return;
		}

		SqlSession sqlSession = null;
		try {
			if (collection != null && !collection.isEmpty()) {
				sqlSession = createSqlSession();
				Iterator<T> iterator = collection.iterator();
				while (iterator.hasNext()) {
					T t = iterator.next();
					if (t != null) {
						log.info("【update a entity [" + entityClass.getName() + "]】\r\n" + t.toString());
						sqlSession.update(namespace + UPDATE_METHOD_NAME, t);
					}
				}
				commit(sqlSession);
			}
		} catch (Exception e) {
			log.error("【Unexpected exception when try to update some of entity [" + entityClass.getName()
					+ "] by batch type】", e);
			rollback(sqlSession);
			throw new ManagerException(e);
		} finally {
			close(sqlSession);
		}
	}

	@Override
	public void batchUpdateByPrimaryKeySelective(Collection<T> collection, String sqlSessionFactoryBeanName)
			throws ManagerException {
		if (!hasSqlSessionFactory(sqlSessionFactoryBeanName)) {
			return;
		}

		batchUpdateByPrimaryKeySelective(collection);
	}

	@Override
	public void batchDeleteByPrimaryKey(Collection<T> collection) throws ManagerException {
		if (!hasSqlSessionFactory()) {
			return;
		}

		batchDeleteByPrimaryKey(collection, sqlSessionFactory, namespace);
	}

	public void batchDeleteByPrimaryKey(Collection<T> collection, SqlSessionFactoryBean sqlSessionFactory,
			String namespace) throws ManagerException {
		if (!hasSqlSessionFactory()) {
			return;
		}

		SqlSession sqlSession = null;
		try {
			if (collection != null && !collection.isEmpty()) {
				sqlSession = createSqlSession();
				Iterator<T> iterator = collection.iterator();
				while (iterator.hasNext()) {
					T t = iterator.next();
					if (t != null) {
						log.info("【delete a entity [" + entityClass.getName() + "]】\r\n" + t.toString());
						sqlSession.delete(namespace + DELETE_METHOD_NAME, t);
					}
				}
				commit(sqlSession);
			}
		} catch (Exception e) {
			log.error("【Unexpected exception when try to delete some of entity [" + entityClass.getName()
					+ "] by batch type】", e);
			rollback(sqlSession);
			throw new ManagerException(e);
		} finally {
			close(sqlSession);
		}
	}

	@Override
	public void batchDeleteByPrimaryKey(Collection<T> collection, String sqlSessionFactoryBeanName)
			throws ManagerException {
		if (!hasSqlSessionFactory(sqlSessionFactoryBeanName)) {
			return;
		}

		batchDeleteByPrimaryKey(collection);
	}

	private boolean hasSqlSessionFactory(String sqlSessionFactoryBeanName) {
		if (getSqlSessionFactory() == null) {
			SqlSessionFactoryBean sqlSessionFactory = (SqlSessionFactoryBean) ApplicationContext
					.getBean(sqlSessionFactoryBeanName, SqlSessionFactoryBean.class);
			if (sqlSessionFactory != null) {
				return true;
			}
		}
		return false;
	}

	private boolean hasSqlSessionFactory() {
		if (getSqlSessionFactory() != null) {
			return true;
		}
		SqlSessionFactoryBean defaultSqlSessionFactory = (SqlSessionFactoryBean) ApplicationContext
				.getBean(DEFAULT_SQLSESSIONFACTORY_NAME, SqlSessionFactoryBean.class);
		if (defaultSqlSessionFactory != null) {
			setSqlSessionFactory(defaultSqlSessionFactory);
			return true;
		}
		return false;
	}

	public SqlSessionFactoryBean getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactoryBean sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public Mapper<T> getMapper() {
		return mapper;
	}

	public void setMapper(Mapper<T> mapper) {
		this.mapper = mapper;
	}

	public Class<?> getMapperClass() {
		return mapperClass;
	}

	public void setMapperClass(Class<?> mapperClass) {
		this.mapperClass = mapperClass;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

}
