package com.wjz.service.cache.lionbridge.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.wjz.service.cache.lionbridge.CacheableExector;
import com.wjz.service.cache.lionbridge.annotation.Cacheable;

/**
 * 拦截标注 {@link Cacheable} 注解的方法，设置或删除缓存
 * 
 * @author wangjz
 * @date 2020年4月28日
 *
 */
public class CacheableInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		Cacheable anno = inv.getMethod().getAnnotation(Cacheable.class);
		if (anno == null) {
			inv.invoke();
		} else {
			new CacheableExector(anno, inv).execte();
		}
	}

}
