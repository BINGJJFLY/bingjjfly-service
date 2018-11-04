package com.wjz.service.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * <b>Dao层转换视图层类注解</b>
 * 
 * @author iss002
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ViewObject {

	/**
	 * ViewObject类型
	 * 
	 * @return
	 */
	@AliasFor("type")
	Class<?> value() default void.class;

	/**
	 * ViewObject类型
	 * 
	 * @return
	 */
	@AliasFor("value")
	Class<?> type() default void.class;

}
