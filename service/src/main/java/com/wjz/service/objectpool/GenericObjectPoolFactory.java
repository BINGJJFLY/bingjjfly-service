package com.wjz.service.objectpool;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 * <b>对象池工厂类</b>
 * 
 * @author iss002
 *
 * @param <T>
 *            池对象
 */
public class GenericObjectPoolFactory<T> extends GenericObjectPoolConfigAssistant {

	protected static GenericObjectPool<?> objectPool(PooledObjectFactory<?> objectFactory, int maxObject) {
		GenericObjectPool<?> objectPool = new GenericObjectPool<>(objectFactory,
				buildGenericObjectPoolConfig(maxObject));
		return objectPool;
	}
}
