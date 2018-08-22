package com.wjz.service.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <b>缓存实例</>
 * <p>
 * 继承了 {@link ConcurrentHashMap} 可以以键值对形式存取缓存内容
 * 
 * @author iss002
 *
 * @param <K>
 * @param <V>
 */
public class Cache<K, V> extends ConcurrentHashMap<K, V> {

	private static final long serialVersionUID = 1433113163348531341L;

	private static final int DEFAULT_CAPACITY = 8;

	public Cache() {
		super(DEFAULT_CAPACITY);
	}

	public Cache(int initialCapacity) {
		super(initialCapacity);
	}

}