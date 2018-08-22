package com.wjz.service.fastdfs;

import java.util.NoSuchElementException;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;

import com.wjz.service.objectpool.GenericObjectPoolFactory;

/**
 * <b>{@code TrackerServer} 对象池类</b>
 * <p>
 * 具备了一切关于对象池的行为
 * </p>
 * 
 * @author iss002
 * @see {@link org.apache.commons.pool2.ObjectPool ObjectPool}
 *
 */
public class TrackerServerPool extends GenericObjectPoolFactory<TrackerServer> {

	private TrackerServerPool() {
	}

	/**
	 * {@code TrackerServer} 对象池
	 */
	private static GenericObjectPool<TrackerServer> trackerServerPool;

	/**
	 * {@code TrackerServer} 对象池的最大容纳数
	 */
	@Value("${max_storage_connection}")
	private static int maxObject;

	@SuppressWarnings("unchecked")
	public static synchronized GenericObjectPool<TrackerServer> getObjectPool() {
		if (trackerServerPool == null) {
			trackerServerPool = (GenericObjectPool<TrackerServer>) objectPool(getPooledObjectFactory(), maxObject);
		}
		return trackerServerPool;
	}

	private static PooledObjectFactory<TrackerServer> getPooledObjectFactory() {
		return new TrackerServerFactory();
	}

	public static TrackerServer borrowObject() throws Exception, NoSuchElementException, IllegalStateException {
		return getObjectPool().borrowObject();
	}

	public static void returnObject(TrackerServer obj) throws Exception {
		getObjectPool().returnObject(obj);
	}

	public static void invalidateObject(TrackerServer obj) throws Exception {
		getObjectPool().invalidateObject(obj);
	}

	public static void addObject() throws Exception, IllegalStateException, UnsupportedOperationException {
		getObjectPool().addObject();
	}

	public static int getNumIdle() {
		return getObjectPool().getNumIdle();
	}

	public static int getNumActive() {
		return getObjectPool().getNumActive();
	}

	public static void clear() throws Exception, UnsupportedOperationException {
		getObjectPool().clear();
	}

	public static void close() {
		getObjectPool().close();
	}

}
