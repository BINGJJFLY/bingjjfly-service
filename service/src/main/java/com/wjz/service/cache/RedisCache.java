package com.wjz.service.cache;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <b>Mybatis二级缓存适配缓存</b>
 *
 * @author iss002
 *
 */
public class RedisCache implements Cache {

	private String id;
	private static RedisTemplate<Object, Object> redisTemplate;

	public RedisCache(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void putObject(Object key, Object value) {
		// 将key和value序列化为byte[]，使用BinaryJedis或者是BinaryJedisCluster设置键值对
		redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public Object getObject(Object key) {
		// 将key序列化为byte[]，使用BinaryJedis或者是BinaryJedisCluster根据键获得value，将value反序列化获得值
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public Object removeObject(Object key) {
		Object value = getObject(key);
		redisTemplate.opsForValue().set(key, null, 0, TimeUnit.MILLISECONDS);
		return value;
	}

	@Override
	public void clear() {
		redisTemplate.getConnectionFactory().getConnection().flushDb();
	}

	@Override
	public int getSize() {
		return redisTemplate.getConnectionFactory().getConnection().dbSize().intValue();
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return null;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
		RedisCache.redisTemplate = redisTemplate;
	}

}
