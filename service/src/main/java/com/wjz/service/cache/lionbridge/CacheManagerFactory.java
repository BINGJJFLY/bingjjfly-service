package com.wjz.service.cache.lionbridge;

/**
 * 缓存管理器创建工厂
 * 
 * @author wangjz
 * @date 2020年4月27日
 *
 */
public interface CacheManagerFactory {

	/**
	 * 获得缓存管理器
	 * 
	 * @return
	 */
	CacheManager getCacheManager();
}
