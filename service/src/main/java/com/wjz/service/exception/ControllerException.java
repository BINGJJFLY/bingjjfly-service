package com.wjz.service.exception;

/**
 * <b>Controller层异常类</b>
 * 
 * @author iss002
 *
 */
public class ControllerException extends ExceptionHolder {

	private static final long serialVersionUID = -2643318428960083124L;

	public ControllerException() {
		super();
	}

	public ControllerException(String message) {
		super(message);
	}

	public ControllerException(Throwable cause) {
		super(cause);
	}

	public ControllerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ControllerException(String code, String message) {
		super(code, message);
	}

	public ControllerException(String code, String message, Throwable cause) {
		super(code, message, cause);
	}

}
