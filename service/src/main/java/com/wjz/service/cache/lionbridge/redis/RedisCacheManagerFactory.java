package com.wjz.service.cache.lionbridge.redis;

import com.wjz.service.cache.lionbridge.CacheManager;
import com.wjz.service.cache.lionbridge.CacheManagerFactory;

/**
 * Redis缓存管理器创建工厂
 * 
 * @author wangjz
 * @date 2020年4月27日
 *
 */
public class RedisCacheManagerFactory implements CacheManagerFactory {

	@Override
	public CacheManager getCacheManager() {
		return new RedisCacheManager();
	}

}
