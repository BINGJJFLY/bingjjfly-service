package com.wjz.service.redis;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.BoundKeyOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <b>抽象Redis缓存行为实现类</b>
 * <p>
 * 缓存公共行为在这里实现如过期、重命名行为
 * <p>
 * 抽象方法 {@code bound(K key)}，返回 {@link BoundKeyOperations} 子接口
 * <p>
 * 其他缓存行为均由子类根据自身特性具体实现
 * <p>
 * {@link RedisTemplate} 支持构造器方式注入、set方式注入
 * 
 * @author iss002
 * @see RedisValueOperations
 *
 */
public abstract class AbstractRedisOperations<K, V> implements RedisOperations<K, V> {

	private RedisTemplate<K, V> redisTemplate;
	
	public AbstractRedisOperations() {}
	
	public AbstractRedisOperations(RedisTemplate<K, V> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@Override
	public Boolean expire(K key, long timeout, TimeUnit unit) {
		return bound(key).expire(timeout, unit);
	}

	@Override
	public Boolean expire(K key, long timeout) {
		return bound(key).expire(timeout, TimeUnit.MILLISECONDS);
	}

	@Override
	public Boolean expireImmediately(K key) {
		return expire(key, Long.valueOf(0));
	}

	@Override
	public Boolean expireAt(K key, Date date) {
		return bound(key).expireAt(date);
	}

	@Override
	public Boolean persist(K key) {
		return bound(key).persist();
	}

	@Override
	public void rename(K oldKey, K newKey) {
		bound(oldKey).rename(newKey);
	}

	public RedisTemplate<K, V> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 子类实现返回BoundKeyOperations的子接口：
	 * <p>
	 * <ul>
	 * <li>{@code BoundValueOperations}</li>
	 * <li>{@code BoundHashOperations}</li>
	 * <li>{@code BoundListOperations}</li>
	 * <li>{@code BoundSetOperations}</li>
	 * <li>{@code BoundZSetOperations}</li>
	 * </ul>
	 * 
	 * @param key Redis属性key
	 * @return
	 */
	protected abstract BoundKeyOperations<K> bound(K key);
	
}
