package com.wjz.service.concurrent.executor;

/**
 * <b>多线程任务执行器</b>
 *
 * @author iss002
 *
 */
public class ThreadTaskExecutor implements TaskExecutor {

	private final ThreadExecutor threadExecutor;

	public ThreadTaskExecutor() {
		threadExecutor = new ThreadExecutor();
	}

	@Override
	public void execute(Runnable task) {
		threadExecutor.execute(task);
	}

}
