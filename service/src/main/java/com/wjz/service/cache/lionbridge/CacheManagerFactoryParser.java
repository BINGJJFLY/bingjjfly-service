package com.wjz.service.cache.lionbridge;

import java.util.HashMap;
import java.util.Map;

import com.wjz.service.cache.lionbridge.CacheManager.CacheManagerType;
import com.wjz.service.cache.lionbridge.redis.RedisCacheManagerFactory;

/**
 * 存储解析缓存管理类型及缓存管理创建工厂之间的映射关系
 * 
 * @author wangjz
 * @date 2020年4月28日
 *
 */
public abstract class CacheManagerFactoryParser {

	private static final Map<CacheManagerType, CacheManagerFactory> mappings = new HashMap<>(1, 1);

	static {
		// 线程安全问题，只会有一个线程执行类的构造器<clinit>()，HashMap的初始化过程线程安全
		mappings.put(CacheManagerType.REDIS, new RedisCacheManagerFactory());
	}

	public static CacheManagerFactory parse(CacheManagerType type) {
		CacheManagerFactory factory;
		if ((factory = mappings.get(type)) == null) {
			throw new RuntimeException("暂不支持的CacheManagerFactory类型");
		}
		return factory;
	}

}
