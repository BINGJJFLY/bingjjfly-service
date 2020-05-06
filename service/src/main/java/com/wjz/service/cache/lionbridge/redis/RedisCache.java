package com.wjz.service.cache.lionbridge.redis;

import com.jfinal.plugin.redis.serializer.FstSerializer;
import com.wjz.service.cache.lionbridge.AbstractValueAdaptingCache;

/**
 * Redis缓存
 * 
 * @author wangjz
 * @date 2020年4月26日
 *
 */
public class RedisCache extends AbstractValueAdaptingCache {

	private final String name;

	public RedisCache(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void put(Object key, Object value) {
//		CacheUtils.set(serializeCacheKey(key), serializeCacheKey(value));
	}
	
	@Override
	public void evict(Object key) {
//		CacheUtils.del(serializeCacheKey(key));
	}

	@Override
	protected Object lookup(Object key) {
		byte[] value = /*CacheUtils.get(serializeCacheKey(key))*/ null;
		if (value == null) {
			return null;
		}
		return deserializeCacheValue(value);
	}

	/**
	 * 获得CacheKey的字节数组，子类可重写，使用独有的序列方式
	 * 
	 * @param key
	 * @return
	 */
	protected byte[] serializeCacheKey(Object key) {
		return FstSerializer.me.valueToBytes(key);
	}

	/**
	 * 获得CacheValue的反序列化对象，子类可重写，使用独有的反序列方式
	 * 
	 * @param value
	 * @return
	 */
	protected Object deserializeCacheValue(byte[] value) {
		return FstSerializer.me.valueFromBytes(value);
	}

}
