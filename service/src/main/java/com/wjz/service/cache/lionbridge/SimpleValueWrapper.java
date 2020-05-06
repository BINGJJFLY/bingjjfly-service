package com.wjz.service.cache.lionbridge;

import com.wjz.service.cache.lionbridge.Cache.ValueWrapper;

/**
 * 默认的缓存值包装对象
 * 
 * @author wangjz
 * @date 2020年4月28日
 *
 */
public class SimpleValueWrapper implements ValueWrapper {

	private final Object value;

	public SimpleValueWrapper(Object value) {
		this.value = value;
	}

	@Override
	public Object get() {
		return this.value;
	}

}
