package com.wjz.service.cache.lionbridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.util.StringUtils;
import com.jfinal.aop.Invocation;
import com.wjz.service.cache.lionbridge.Cache.ValueWrapper;
import com.wjz.service.cache.lionbridge.CacheManager.CacheManagerType;
import com.wjz.service.cache.lionbridge.annotation.Cacheable;

/**
 * 执行缓存设置或删除操作
 * 
 * @author wangjz
 * @date 2020年4月28日
 *
 */
public class CacheableExector {

	private static final Logger logger = LoggerFactory.getLogger(CacheableExector.class);

	private final String cacheName;
	private final String key;
	private final Boolean evict;
	private final CacheManager cacheManager;
	private final Invocation inv;

	public CacheableExector(Cacheable a, Invocation inv) {
		String n, k;
		checkValid(n = a.value(), k = a.key());
		this.cacheName = n;
		this.evict = a.evict();
		this.key = k;
		this.cacheManager = parseCacheManagerFactory(a.cacheManager()).getCacheManager();
		this.inv = inv;
	}

	private CacheManagerFactory parseCacheManagerFactory(CacheManagerType type) {
		return CacheManagerFactoryParser.parse(type);
	}

	private void checkValid(String cacheName, String key) {
		if (StringUtils.isEmpty(cacheName)) {
			throw new RuntimeException("缓存名不能为空");
		}
		if (StringUtils.isEmpty(key)) {
			throw new RuntimeException("缓存键不能为空");
		}
	}

	public void execte() {
		Cache cache = cacheManager.getCache(cacheName);
		if (evict) {
			processEvict(cache);
			inv.invoke();
			return;
		}
		Object res = processGet(cache);
		if (res != null) {
			inv.setReturnValue(res);
			return;
		}
		inv.invoke();
		processPut(cache, inv.getReturnValue());
	}

	private void processEvict(Cache cache) {
		try {
			beforeEvict();
			cache.evict(key);
			if (logger.isDebugEnabled()) {
				logger.debug("【删除缓存】：key[{}]", key);
			}
		} catch (Exception e) {
			logger.error("【删除缓存时异常】：key[{}]", key);
			throw e;
		}
	}

	protected void beforeEvict() {

	}

	private void processPut(Cache cache, Object returnValue) {
		try {
			cache.put(key, returnValue);
			if (logger.isDebugEnabled()) {
				logger.debug("【设置缓存】：key[{}]，value[{}]", key, returnValue);
			}
		} catch (Exception e) {
			logger.error("【设置缓存时异常】：key[{}]", key);
			throw e;
		}
	}

	private Object processGet(Cache cache) {
		Object res = null;
		try {
			ValueWrapper valueWrapper = cache.get(key);
			if (valueWrapper != null) {
				res = valueWrapper.get();
			}
		} catch (Exception e) {
			logger.error("【查询缓存时异常】：key[{}]", key);
			throw e;
		}
		return res;
	}
}
