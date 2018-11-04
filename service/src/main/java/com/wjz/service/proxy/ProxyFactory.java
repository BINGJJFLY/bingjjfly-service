package com.wjz.service.proxy;

public interface ProxyFactory<T> {

	/**
	 * 创建目标对象的代理对象
	 * <ul>
	 * <li>静态代理（代理模式）</li>
	 * <li>Jdk动态代理</li>
	 * <li>Javassist动态代理</li>
	 * </ul>
	 * 
	 * @param t
	 *            目标对象
	 * @return
	 */
	Proxy<T> createProxy(T t);
}
