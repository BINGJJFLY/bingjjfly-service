package com.wjz.service.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 属性处理器扩展注解
 * @author 123
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PropertyHandler {

	/**
	 * 属性类型
	 * 
	 * @return
	 */
	Class<?> value();

}
