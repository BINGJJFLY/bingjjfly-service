package com.wjz.service.objectpool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * <b>抽象的池对象工厂类</b>
 * <p>
 * 继续抽象化 {@code create()} 方法，公共 {@code wrap(T obj)} 方法
 * </p>
 * 
 * @author iss002
 *
 * @param <T>
 *            池对象
 */
public abstract class AbstractBasePooledObjectFactory<T> extends BasePooledObjectFactory<T> {

	@Override
	public PooledObject<T> wrap(T obj) {
		return new DefaultPooledObject<T>(obj);
	}

}
