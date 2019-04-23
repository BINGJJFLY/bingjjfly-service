package com.wjz.service.manager.delete.batch;

import java.util.Collection;
import java.util.Iterator;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;

import com.wjz.service.manager.ManagerException;
import com.wjz.service.manager.update.batch.BaseBatchUpdateService;

import tk.mybatis.mapper.common.Mapper;

/**
 * <b>批量删除操作</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public abstract class BaseBatchDeleteService<T, M extends Mapper<T>> extends BaseBatchUpdateService<T, M> {

	public BaseBatchDeleteService(M mapper) {
		super(mapper);
	}

	@Override
	public void batchDeleteByPrimaryKey(Collection<T> collection) throws ManagerException {
		batchDeleteByPrimaryKey(collection, getSqlSessionFactory(), getNamespace());
	}

	public void batchDeleteByPrimaryKey(Collection<T> collection, SqlSessionFactoryBean sqlSessionFactory,
			String namespace) throws ManagerException {
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
		setSqlSessionFactory(sqlSessionFactoryBeanName);
		batchDeleteByPrimaryKey(collection);
	}

}
