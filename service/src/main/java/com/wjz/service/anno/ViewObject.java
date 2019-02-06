package com.wjz.service.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <b>DomainObject转换ViewObject注解</b>
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
	Class<?> value();

}
