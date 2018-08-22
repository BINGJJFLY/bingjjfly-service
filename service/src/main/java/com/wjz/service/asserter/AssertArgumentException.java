package com.wjz.service.asserter;

import com.wjz.service.exception.ServiceException;

/**
 * <b>断言异常类</b>
 * <p>
 * 预期入参非法如入参为 {@link null} 时抛出该异常
 * 
 * @author iss002
 *
 */
@SuppressWarnings("serial")
public class AssertArgumentException extends ServiceException {

	public AssertArgumentException() {
		super();
	}

	public AssertArgumentException(String message) {
		super(message);
	}

	public AssertArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public AssertArgumentException(Throwable cause) {
		super(cause);
	}

}
