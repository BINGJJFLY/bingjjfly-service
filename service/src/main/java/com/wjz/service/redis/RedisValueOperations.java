package com.wjz.service.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <b>Redis字符串类型缓存实现类</b>
 * <p>
 * 具体实现Redis字符串类型的缓存行为：存、取、更新、删除
 * <p>
 * 存储对象步骤是：
 * <ul>
 * <li>使用序列化工具 {@code SerializingUtils} 将对象序列化得到字节数组</li>
 * <li>将字节数组转化为字符串：String cache = new String(bytes, "UTF-8");</li>
 * <li>存储字符串</li>
 * </ul>
 * <p>
 * 获取对象步骤是：
 * <ul>
 * <li>获得字符串</li>
 * <li>将字符串转化为字节数组：byte[] bytes = cacheStr.getBytes();</li>
 * <li>使用反序列化工具 {@code SerializingUtils} 将字节数组反序列化为对象</li>
 * </ul>
 * 
 * @author iss002
 *
 */
public class RedisValueOperations extends AbstractRedisOperations<String, String> {

	public RedisValueOperations() {
		super();
	}
	
	public RedisValueOperations(RedisTemplate<String, String> redisTemplate) {
		super(redisTemplate);
	}
	
	@Override
	protected BoundValueOperations<String, String> bound(String key) {
		return getRedisTemplate().boundValueOps(key);
	}

	@Override
	public String get(String key) {
		return bound(key).get();
	}

	@Override
	public void put(String key, String value) {
		bound(key).set(value);
	}

	@Override
	public void put(String key, String value, long timeout) {
		put(key, value, timeout, TimeUnit.MILLISECONDS);
	}

	@Override
	public void put(String key, String value, long timeout, TimeUnit unit) {
		bound(key).set(value, timeout, unit);
	}

	@Override
	public void update(String key, String value) {
		put(key, value);
	}

	@Override
	public void delete(String key) {
		super.expireImmediately(key);
	}

}
