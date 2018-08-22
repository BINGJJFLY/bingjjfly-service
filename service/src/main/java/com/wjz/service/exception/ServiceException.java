package com.wjz.service.exception;

/**
 * <b>Service层异常类</b>
 * 
 * @author iss002
 *
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -7471107603553559221L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
