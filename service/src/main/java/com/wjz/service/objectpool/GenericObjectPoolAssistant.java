package com.wjz.service.objectpool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wjz.service.exception.ServiceException;

/**
 * <b>抽象的对象池助手类</b>
 * <p>
 * 该类主要是为具体的对象池提供池功能和池的相关配置
 * </p>
 * 
 * @author iss002
 * @see {@link com.wjz.service.fastdfs.TrackerServerPool TrackerServerPool}
 *
 * @param <T>
 *            对象池容纳的对象
 */
public abstract class GenericObjectPoolAssistant<T> {

	private static final Logger log = LoggerFactory.getLogger(GenericObjectPoolAssistant.class);

	/**
	 * 对象池的最小空闲对象数
	 */
	private static final int MIN_IDLE = 2;

	/**
	 * 对象池的最大对象容纳数
	 */
	private int maxObject;

	public GenericObjectPool<T> objectPool(BasePooledObjectFactory<T> objectFactory) {
		GenericObjectPool<T> objectPool = new GenericObjectPool<>(objectFactory, buildGenericObjectPoolConfig());
		return objectPool;
	}

	private GenericObjectPoolConfig buildGenericObjectPoolConfig() {
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMinIdle(MIN_IDLE);
		int maxTotal = getMaxObject();
		if (maxTotal > 0) {
			poolConfig.setMaxTotal(maxTotal);
		} else {
			String message = "对象池的最大对象容纳数不能小于等于0";
			log.error(message);
			throw new ServiceException(message);
		}
		return poolConfig;
	}

	public int getMaxObject() {
		return maxObject;
	}

	public void setMaxObject(int maxObject) {
		this.maxObject = maxObject;
	}

}
