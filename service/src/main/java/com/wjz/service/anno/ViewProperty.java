package com.wjz.service.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * <b>Domain对象转换View对象属性注解</b>
 * 
 * @author iss002
 *
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ViewProperty {

	/**
	 * View对象属性类型
	 * 
	 * @return
	 */
	@AliasFor("name")
	String value() default "";

	/**
	 * View对象属性类型
	 * 
	 * @return
	 */
	@AliasFor("value")
	String name() default "";

	/**
	 * View对象属性日期或金额格式
	 * 
	 * @return
	 */
	String pattern() default "";

	/**
	 * View对象属性是否进行对称加密
	 * 
	 * @return
	 */
	boolean crypto() default false;

	/**
	 * 是否进行赋值
	 * 
	 * @return
	 */
	boolean working() default true;

	/**
	 * 是否进行转换
	 * <p>
	 * 进行赋值但是不进行转换
	 * </p>
	 * 
	 * @return
	 */
	boolean convertible() default true;

}
