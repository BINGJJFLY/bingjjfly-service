package com.wjz.demo.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wjz.service.proxy.Proxy;

/**
 * Subject的代理类，实现了Subject接口
 * <p>
 * 代理类和委托类实现相同的接口
 * </p>
 * 
 * @author iss002
 *
 */
public class SubjectProxy implements Proxy<Subject>, Subject {
	
	/**
	 * 日志功能交给代理类（缓存等功能）
	 */
	private static final Logger log = LoggerFactory.getLogger(SubjectProxy.class);

	private Subject subject;

	public SubjectProxy() {
	}

	public SubjectProxy(Subject target) {
		this.subject = target;
	}

	@Override
	public void setTarget(Subject target) {
		this.subject = target;
	}

	@Override
	public void operation() {
		log.info("executing method.");
		System.out.println("pre handle...");
		if (subject != null) {
			subject.operation();
			System.out.println("post handle...");
		}
	}

}
