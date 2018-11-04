package com.wjz.demo.proxy;

import com.wjz.service.proxy.Proxy;
import com.wjz.service.proxy.ProxyFactory;

public class SubjectProxyFactory implements ProxyFactory<Subject> {

	@Override
	public Proxy<Subject> createProxy(Subject t) {
		return new SubjectProxy(t);
	}

}
