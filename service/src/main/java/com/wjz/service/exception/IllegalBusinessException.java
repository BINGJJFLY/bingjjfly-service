package com.wjz.service.exception;

import com.wjz.service.exception.ServiceException;

/**
 * <b>Business Service层异常类</b>
 * <p>
 * 不符合业务需求时抛出该异常
 * </p>
 * 
 * @author iss002
 *
 */
@SuppressWarnings("serial")
public class IllegalBusinessException extends ServiceException {

	public IllegalBusinessException() {
		super();
	}

	public IllegalBusinessException(String message) {
		super(message);
	}

	public IllegalBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalBusinessException(Throwable cause) {
		super(cause);
	}

}
