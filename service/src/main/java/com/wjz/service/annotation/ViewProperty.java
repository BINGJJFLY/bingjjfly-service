package com.wjz.service.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewProperty {

	/**
	 * ViewObject对象属性类型
	 * 
	 * @return
	 */
	@AliasFor("name")
	String value() default "";

	/**
	 * ViewObject对象属性类型
	 * 
	 * @return
	 */
	@AliasFor("value")
	String name() default "";

	/**
	 * ViewObject对象属性日期或金额格式
	 * 
	 * @return
	 */
	String pattern() default "";
	
	/**
	 * ViewObject对象属性是否进行对称加密
	 * @return
	 */
	boolean crypto() default false;
}
