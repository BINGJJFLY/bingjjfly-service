package com.wjz.service.cache;

/**
 * <b>抽象缓存接口实现类</b>
 * <p>
 * 具体实现了存取缓存的行为
 * </p>
 * 
 * @author iss002
 *
 */
public abstract class AbstractCacheServiceImpl implements CacheService {

	private static Cache<String, Object> cache = null;
	
	private final Object lock = new Object();

	@Override
	public Cache<String, Object> getCacheInstance() {
		synchronized (this.lock) {
			if (cache == null) {
				cache = new Cache<String, Object>();
			}
		}
		return cache;
	}

	@Override
	public Object getCache(String key) {
		return getCacheInstance().get(key);
	}

	@Override
	public void putCache(String key, Object value) {
		synchronized (this.lock) {
			getCacheInstance().put(key, value);
		}
	}
	
	@Override
	public void clearCache(String key) {
		synchronized (this.lock) {
			getCacheInstance().remove(key);
		}
	}
	
	@Override
	public void clearAllCache() {
		synchronized (this.lock) {
			getCacheInstance().clear();
		}
	}
	
}
