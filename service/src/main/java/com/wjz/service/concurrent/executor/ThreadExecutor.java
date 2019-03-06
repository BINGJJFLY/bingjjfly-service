package com.wjz.service.concurrent.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * <b>线程执行器</b>
 *
 * @author iss002
 *
 */
public class ThreadExecutor implements Executor {

	private volatile ThreadFactory factory;

	public ThreadExecutor() {
		factory = Executors.defaultThreadFactory();
	}

	@Override
	public void execute(Runnable task) {
		Thread t = factory.newThread(task);
		t.start();
	}

}
