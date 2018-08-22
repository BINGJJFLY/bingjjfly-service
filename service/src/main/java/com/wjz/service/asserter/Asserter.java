package com.wjz.service.asserter;

/**
 * <b>断言器</b>
 * <p>
 * 该接口定义入参不能为 {@link null} 等行为
 * 
 * @author iss002
 * @see DefaultAsserter
 *
 */
public interface Asserter {

	void assertArgumentNotNull(Object arg, String message);

	void assertArgumentsNotNull(Object[] args, String message);

}
