package com.wjz.service.async;

/**
 * <b>任务异步执行器</b>
 *
 * @author iss002
 *
 * @param <T>
 */
public abstract class TaskRunnable<T> implements Runnable {
	
	private T t;
	
	public TaskRunnable() {
		setT(t);
	}

	@Override
	public void run() {
		if (t != null) {
			doRun(t);
		}
	}

	public void setT(T t) {
		this.t = t;
	}
	
	protected abstract void doRun(T t);

}
