package com.wjz.service.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

/**
 * <b>应用上下文</b>
 * <p>
 * 只开放获得Bean的行为
 * 
 * @author iss002
 *
 */
public abstract class ApplicationContext implements ApplicationContextAware {

	private static org.springframework.context.ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext)
			throws BeansException {
		ApplicationContext.applicationContext = applicationContext;
	}

	private static org.springframework.context.ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String beanName) {
		if (StringUtils.isEmpty(beanName)) {
			return null;
		}
		return getApplicationContext().getBean(beanName);
	}

	public static Object getBean(Class<?> beanType) {
		if (beanType == null) {
			return null;
		}
		return getApplicationContext().getBean(beanType);
	}

	public static Object getBean(String beanName, Class<?> beanType) {
		if (StringUtils.isEmpty(beanName)) {
			return null;
		}
		return getApplicationContext().getBean(beanName, beanType);
	}

}
