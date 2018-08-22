package com.wjz.service.objectpool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wjz.service.exception.ServiceException;

/**
 * <b>抽象的对象池助手类</b>
 * 
 * @author iss002
 *
 */
public class GenericObjectPoolConfigAssistant {

	private static final Logger log = LoggerFactory.getLogger(GenericObjectPoolConfigAssistant.class);

	/**
	 * 对象池的最小空闲对象数
	 */
	private static final int MIN_IDLE = 2;

	protected static GenericObjectPoolConfig buildGenericObjectPoolConfig(int maxObject) {
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMinIdle(MIN_IDLE);
		if (maxObject > 0) {
			poolConfig.setMaxTotal(maxObject);
		} else {
			String message = "对象池的最大对象容纳数不能小于等于0";
			log.error(message);
			throw new ServiceException(message);
		}
		return poolConfig;
	}

}
