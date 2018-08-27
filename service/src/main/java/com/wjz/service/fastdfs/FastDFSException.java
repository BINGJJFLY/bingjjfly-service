package com.wjz.service.fastdfs;

import com.wjz.service.exception.ServiceException;

/**
 * <b>FastDFS分布式文件系统异常类</b>
 * 
 * @author iss002
 *
 */
@SuppressWarnings("serial")
public class FastDFSException extends ServiceException {

	public FastDFSException() {
		super();
	}

	public FastDFSException(String message) {
		super(message);
	}

	public FastDFSException(String message, Throwable cause) {
		super(message, cause);
	}

	public FastDFSException(Throwable cause) {
		super(cause);
	}

}
