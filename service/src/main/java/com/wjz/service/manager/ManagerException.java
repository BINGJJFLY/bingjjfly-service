package com.wjz.service.manager;

import com.wjz.service.exception.ServiceException;

/**
 * <b>Manager Service层异常类</b>
 * 
 * @author iss002
 *
 */
@SuppressWarnings("serial")
public class ManagerException extends ServiceException {

	public ManagerException() {
		super();
	}

	public ManagerException(String message) {
		super(message);
	}

	public ManagerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ManagerException(Throwable cause) {
		super(cause);
	}

}
