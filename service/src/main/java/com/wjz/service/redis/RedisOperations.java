package com.wjz.service.redis;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <b>Redis缓存行为接口</b>
 * <p>
 * 缓存行为包括：存、取、更新、删除、过期、重命名
 * <p>
 * <b>TODO</b> RedisConnectionCommands负责切换Redis存储库 0-15，此处只负责上述行为
 * <p>
 * <b>TODO</b> 异常设计
 * 
 * @author iss002
 *
 * @param <K>
 * @param <V>
 */
public interface RedisOperations<K, V> {

	// 获得缓存值
	V get(K key);

	// 设置缓存值
	void put(K key, V value);

	void put(K key, V value, long timeout);

	void put(K key, V value, long timeout, TimeUnit unit);

	// 更新缓存值
	void update(K key, V value);

	// 删除缓存值
	void delete(K key);

	// 设置过期策略
	Boolean expire(K key, long timeout, TimeUnit unit);

	Boolean expire(K key, long timeout);

	Boolean expireImmediately(K key);

	Boolean expireAt(K key, Date date);

	// 到期删除
	Boolean persist(K key);

	// 重命名key
	void rename(K oldKey, K newKey);

}
