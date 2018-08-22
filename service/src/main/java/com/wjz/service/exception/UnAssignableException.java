package com.wjz.service.exception;

/**
 * <b>类型转换异常类</b>
 * 
 * @author iss002
 *
 */
@SuppressWarnings("serial")
public class UnAssignableException extends ServiceException {

	public UnAssignableException() {
		super();
	}

	public UnAssignableException(String message) {
		super(message);
	}

	public UnAssignableException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnAssignableException(Throwable cause) {
		super(cause);
	}

}
