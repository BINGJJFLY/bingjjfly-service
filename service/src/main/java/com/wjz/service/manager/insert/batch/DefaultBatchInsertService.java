package com.wjz.service.manager.insert.batch;

import java.util.Collection;
import java.util.Iterator;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;

import com.wjz.service.manager.ManagerException;
import com.wjz.service.manager.insert.DefaultInsertService;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>默认的批量插入操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public class DefaultBatchInsertService<T> extends DefaultInsertService<T> implements BatchInsertService<T> {

	public DefaultBatchInsertService(Mapper<T> mapper) {
		super(mapper);
	}

	@Override
	public void batchInsertSelective(Collection<T> collection) throws ManagerException {
		batchInsertSelective(collection, getSqlSessionFactory(), getNamespace());
	}
	
	public void batchInsertSelective(Collection<T> collection, SqlSessionFactoryBean sqlSessionFactory,
			String namespace) throws ManagerException {
		SqlSession sqlSession = null;
		try {
			if (collection != null && !collection.isEmpty()) {
				sqlSession = createSqlSession();
				Iterator<T> iterator = collection.iterator();
				while (iterator.hasNext()) {
					T t = iterator.next();
					if (t != null) {
						log.info("【insert a new entity [" + getEntityClass().getName() + "]】\r\n" + t.toString());
						sqlSession.insert(namespace + INSERT_METHOD_NAME, t);
					}
				}
				commit(sqlSession);
			}
		} catch (Exception e) {
			log.error("【Unexpected exception when try to insert some of entity [" + getEntityClass().getName()
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
		setSqlSessionFactory(sqlSessionFactoryBeanName);
		batchInsertSelective(collection);
	}

}
