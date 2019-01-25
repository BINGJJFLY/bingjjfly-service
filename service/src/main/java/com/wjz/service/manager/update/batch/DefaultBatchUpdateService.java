package com.wjz.service.manager.update.batch;

import java.util.Collection;
import java.util.Iterator;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;

import com.wjz.service.manager.ManagerException;
import com.wjz.service.manager.update.DefaultUpdateService;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>默认的批量更新操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public class DefaultBatchUpdateService<T> extends DefaultUpdateService<T> implements BatchUpdateService<T> {

	public DefaultBatchUpdateService(Mapper<T> mapper) {
		super(mapper);
	}

	@Override
	public void batchUpdateByPrimaryKeySelective(Collection<T> collection) throws ManagerException {
		batchUpdateByPrimaryKeySelective(collection, getSqlSessionFactory(), getNamespace());
	}

	public void batchUpdateByPrimaryKeySelective(Collection<T> collection, SqlSessionFactoryBean sqlSessionFactory,
			String namespace) throws ManagerException {
		SqlSession sqlSession = null;
		try {
			if (collection != null && !collection.isEmpty()) {
				sqlSession = createSqlSession();
				Iterator<T> iterator = collection.iterator();
				while (iterator.hasNext()) {
					T t = iterator.next();
					if (t != null) {
						log.info("【update a entity [" + getEntityClass().getName() + "]】\r\n" + t.toString());
						sqlSession.update(namespace + UPDATE_METHOD_NAME, t);
					}
				}
				commit(sqlSession);
			}
		} catch (Exception e) {
			log.error("【Unexpected exception when try to update some of entity [" + getEntityClass().getName()
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
		setSqlSessionFactory(sqlSessionFactoryBeanName);
		batchUpdateByPrimaryKeySelective(collection);
	}

}
