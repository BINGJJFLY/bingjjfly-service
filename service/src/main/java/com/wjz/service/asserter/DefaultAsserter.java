package com.wjz.service.asserter;

import org.springframework.util.Assert;

/**
 * <b>默认断言器</b>
 * <p>
 * 使用Spring的断言工具类 {@link Assert} 实现断言器定义的行为
 * 
 * @author iss002
 *
 */
public class DefaultAsserter implements Asserter {

	public void assertArgumentNotNull(Object arg, String message) {
		try {
			Assert.notNull(arg, message);
		} catch (IllegalArgumentException e) {
			throw new AssertArgumentException(message);
		}
	}

	public void assertArgumentsNotNull(Object[] args, String message) {
		try {
			Assert.noNullElements(args, message);
		} catch (IllegalArgumentException e) {
			throw new AssertArgumentException(message);
		}
	}

}
