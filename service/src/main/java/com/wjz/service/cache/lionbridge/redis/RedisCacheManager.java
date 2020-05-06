package com.wjz.service.cache.lionbridge.redis;

import com.wjz.service.cache.lionbridge.AbstractCacheManager;
import com.wjz.service.cache.lionbridge.Cache;

/**
 * Redis缓存管理器
 * 
 * @author wangjz
 * @date 2020年4月26日
 *
 */
public class RedisCacheManager extends AbstractCacheManager {
	
	@Override
	protected Cache getMissingCache(String name) {
		return createRedisCache(name);
	}

	protected Cache createRedisCache(String name) {
		return new RedisCache(name);
	}
}
