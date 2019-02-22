package com.wjz.service.exception;

/**
 * <b>异常信息持有者</b>
 *
 * @author iss002
 *
 */
public class ExceptionHolder extends RuntimeException {

	private static final long serialVersionUID = -2490121975821427616L;

	private String code;

	public ExceptionHolder() {
		super();
	}

	public ExceptionHolder(String message) {
		super(message);
	}

	public ExceptionHolder(Throwable cause) {
		super(cause);
	}

	public ExceptionHolder(String message, Throwable cause) {
		super(message, cause);
	}

	public ExceptionHolder(String code, String message) {
		super(message);
		this.setCode(code);
	}

	public ExceptionHolder(String code, String message, Throwable cause) {
		super(message, cause);
		this.setCode(code);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
