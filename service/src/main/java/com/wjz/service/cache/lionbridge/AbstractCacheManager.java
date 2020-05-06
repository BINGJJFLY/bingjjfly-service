package com.wjz.service.cache.lionbridge;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 缓存 {@link Cache} 对象不必每次都需要创建
 * 
 * @author wangjz
 * @date 2020年4月28日
 *
 */
public abstract class AbstractCacheManager implements CacheManager {

	private final ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<>(16);

	@Override
	public Cache getCache(String name) {
		// 检查Map中是否已经存在Cache对象了
		Cache cache = cacheMap.get(name);
		if (cache != null) {
			return cache;
		}
		// 不在缓存中，创建Cache对象
		Cache missingCache = getMissingCache(name);
		if (missingCache != null) {
			// 涉及Map的get()、put()复合操作需要加锁
			synchronized (cacheMap) {
				// 再次检查是否已经存在对应的Cache对象了
				cache = cacheMap.get(name);
				if (cache == null) {
					cache = missingCache;
					cacheMap.put(name, cache);
				}
			}
		}
		return cache;
	}

	/**
	 * 创建一个Cache对象
	 * 
	 * @param name
	 * @return
	 */
	protected abstract Cache getMissingCache(String name);

}
