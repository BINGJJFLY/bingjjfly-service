package com.wjz.demo.proxy;

/**
 * 
 * Subject的实现类，实现了Subject接口
 * <p>
 * 代理类和委托类实现相同的接口
 * </p>
 * 
 * @author iss002
 *
 */
public class SubjectImpl implements Subject {

	@Override
	public void operation() {
		System.out.println("具体实现类处理...");
	}

}
