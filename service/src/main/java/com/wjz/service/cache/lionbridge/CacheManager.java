package com.wjz.service.cache.lionbridge;

/**
 * 缓存管理器
 * 
 * @author wangjz
 * @date 2020年4月26日
 *
 */
public interface CacheManager {

	/**
	 * 根据缓存名获得缓存
	 * 
	 * @param name
	 * @return
	 */
	Cache getCache(String name);

	/**
	 * 缓存管理器类型
	 * 
	 * @author wangjz
	 * @date 2020年4月26日
	 *
	 */
	enum CacheManagerType {
		REDIS;
	}
}
