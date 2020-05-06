package com.wjz.service.cache.lionbridge.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.wjz.service.cache.lionbridge.CacheManager.CacheManagerType;

/**
 * 目标方法是否需要存储或删除缓存的标识注解
 * 
 * <p>
 * {@link #evict} 方法返回值为true时，只删除缓存不存储缓存，否则将缓存目标方法返回值
 * </p>
 * 
 * @author wangjz
 * @date 2020年4月28日
 *
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Cacheable {

	/**
	 * 缓存组名
	 * 
	 * @return
	 */
	String value() default "";

	/**
	 * 缓存Key
	 * 
	 * @return
	 */
	String key() default "";

	/**
	 * 是否驱逐旧数据
	 * 
	 * @return
	 */
	boolean evict() default false;

	/**
	 * 缓存管理器
	 * 
	 * @return
	 */
	CacheManagerType cacheManager() default CacheManagerType.REDIS;
}
