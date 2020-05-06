package com.wjz.service.cache.lionbridge;

/**
 * 缓存值包装
 * 
 * @author wangjz
 * @date 2020年4月28日
 *
 */
public abstract class AbstractValueAdaptingCache implements Cache {

	@Override
	public ValueWrapper get(Object key) {
		Object cache = lookup(key);
		return toValueWrapper(cache);
	}

	/**
	 * 默认使用SimpleValueWrapper，子类可以重写
	 * 
	 * @param storeValue
	 * @return
	 */
	protected ValueWrapper toValueWrapper(Object storeValue) {
		return storeValue != null ? new SimpleValueWrapper(storeValue) : null;
	}

	/**
	 * 查找缓存值
	 * 
	 * @param key
	 * @return
	 */
	protected abstract Object lookup(Object key);

}
