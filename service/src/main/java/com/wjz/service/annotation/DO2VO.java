package com.wjz.service.annotation;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Deprecated
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DO2VO {
	
	/**
	 * 注解目标方法是否需要返回值DO类型转VO类型
	 * @return
	 */
	boolean isTranslatable() default false;
	
	/**
	 * DO类型
	 * @return
	 */
	Class<? extends Serializable> DOType();
	
	/**
	 * VO类型
	 * @return
	 */
	Class<?> VOType() default void.class;
	
}
