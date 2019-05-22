package com.wjz.service.cache;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * RedisTemplate提供商
 * <p>
 * spring-redis.xml配置文件中配置：<br>
 * &lt;bean class="com.wjz.service.cache.RedisTemplateProvider"&gt;<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;property name="redisTemplate" ref="redisTemplate"
 * /&gt;<br>
 * &lt;/bean&gt;
 * </p>
 * <p>
 * mybatis-config.xml配置文件中配置：<br>
 * &lt;typeAliases&gt;<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;typeAlias type="com.wjz.service.cache.RedisCache"
 * alias="redisCache" /&gt;<br>
 * &lt;/typeAliases&gt;
 * </p>
 * <p>
 * mapping.xml配置文件中配置：<br>
 * &lt;cache type="redisCache" /&gt;
 * </p>
 * 
 * @author iss002
 *
 */
public class RedisTemplateProvider {

	public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
		RedisCache.setRedisTemplate(redisTemplate);
	}

}
