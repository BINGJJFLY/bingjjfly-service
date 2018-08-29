package com.wjz.service.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

/**
 * <b>Spring应用上下文持有者</b>
 * <p>
 * 需要将该bean交由Spring管理-注解方式或者是XML文件配置方式
 * </p>
 * 
 * @author iss002
 *
 */
public class ApplicationContextHolder implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextHolder.applicationContext = applicationContext;
	}

	private static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String beanName) {
		if (StringUtils.isEmpty(beanName)) {
			throw new NullPointerException("beanName is null");
		}
		return getApplicationContext().getBean(beanName);
	}

	public static Object getBean(Class<?> beanType) {
		if (beanType == null) {
			throw new NullPointerException("beanType is null");
		}
		return getApplicationContext().getBean(beanType);
	}

	public static Object getBean(String beanName, Class<?> beanType) {
		if (StringUtils.isEmpty(beanName) || beanType == null) {
			throw new NullPointerException("beanName [" + beanName + "] or beanType [" + beanType + "] is null");
		}
		return getApplicationContext().getBean(beanName, beanType);
	}

}
