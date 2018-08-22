package com.wjz.service.cache;

import java.util.Map;

/**
 * <b>缓存接口</b>
 * <p>
 * 继承了该接口的类都具备了缓存功能
 * 
 * @author iss002
 * @see AbstractCacheServiceImpl
 */
public interface CacheService {

	Map<String, Object> getCacheInstance();

	Object getCache(String key);

	void putCache(String key, Object value);

	void clearCache(String key);

	void clearAllCache();

}
