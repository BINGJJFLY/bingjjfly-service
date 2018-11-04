package com.wjz.service.exception;

/**
 * <b>Controller层异常类</b>
 * 
 * @author iss002
 *
 */
public class ControllerException extends RuntimeException {

	private static final long serialVersionUID = -2643318428960083124L;

	public ControllerException() {
		super();
	}

	public ControllerException(String message) {
		super(message);
	}

	public ControllerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ControllerException(Throwable cause) {
		super(cause);
	}

}
