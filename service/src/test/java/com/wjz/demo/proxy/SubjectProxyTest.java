package com.wjz.demo.proxy;

import org.junit.Test;

import com.wjz.service.proxy.ProxyFactory;

public class SubjectProxyTest {

	@Test
	public void subjectProxy() {
		ProxyFactory<Subject> proxyFactory = new SubjectProxyFactory();
		SubjectProxy proxy = (SubjectProxy) proxyFactory.createProxy(new SubjectImpl());
		proxy.operation();
	}
}
