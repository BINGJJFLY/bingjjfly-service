package com.wjz.service.async;

import java.util.concurrent.Callable;

/**
 * <b>任务异步执行器</b>
 *
 * @author iss002
 *
 * @param <T>
 * @param <V>
 */
public abstract class TaskCallable<T, V> implements Callable<V> {
	
	private T t;

	@Override
	public V call() throws Exception {
		if (t != null) {
			return doCall(t);
		}
		return null;
	}

	public void setV(T t) {
		this.t = t;
	}

	protected abstract V doCall(T t);
	
}
