package com.wjz.service.cache.lionbridge;

/**
 * 缓存
 * 
 * @author wangjz
 * @date 2020年4月26日
 *
 */
public interface Cache {

	/**
	 * 获得缓存的名
	 * 
	 * @return
	 */
	String getName();

	/**
	 * 根据缓存的Key获得缓存Value包装对象
	 * 
	 * @param key
	 * @return
	 */
	ValueWrapper get(Object key);

	/**
	 * 设置缓存
	 * 
	 * @param key
	 * @param value
	 */
	void put(Object key, Object value);

	/**
	 * 删除缓存
	 * 
	 * @param key
	 */
	void evict(Object key);

	/**
	 * 缓存值的包装对象
	 * 
	 * @author wangjz
	 * @date 2020年4月26日
	 *
	 */
	interface ValueWrapper {

		/**
		 * 获得缓存原值
		 * 
		 * @return
		 */
		Object get();
	}
}
