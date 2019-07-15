package com.wjz.demo.concurrent.executor.scheduled;

import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CustomScheduledExecutor extends ScheduledThreadPoolExecutor {

	public CustomScheduledExecutor(int corePoolSize) {
		super(corePoolSize);
	}

	static class CustomTask<V> implements RunnableScheduledFuture<V> {
		
		Runnable r;
		RunnableScheduledFuture<V> task;
		Callable<V> c;
		
		CustomTask(Runnable r, RunnableScheduledFuture<V> task) {
			this.r = r;
			this.task = task;
		}
		
		CustomTask(Callable<V> c, RunnableScheduledFuture<V> task) {
			this.c = c;
			this.task = task;
		}

		@Override
		public void run() {
			
		}

		@Override
		public boolean cancel(boolean mayInterruptIfRunning) {
			return false;
		}

		@Override
		public boolean isCancelled() {
			return false;
		}

		@Override
		public boolean isDone() {
			return false;
		}

		@Override
		public V get() throws InterruptedException, ExecutionException {
			return null;
		}

		@Override
		public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
			return null;
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return 0;
		}

		@Override
		public int compareTo(Delayed o) {
			return 0;
		}

		@Override
		public boolean isPeriodic() {
			return false;
		}
		
	}
	
	@Override
	protected <V> RunnableScheduledFuture<V> decorateTask(Runnable r, RunnableScheduledFuture<V> task) {
		return new CustomTask<>(r, task);
	}
	
	@Override
	protected <V> RunnableScheduledFuture<V> decorateTask(Callable<V> c, RunnableScheduledFuture<V> task) {
		return new CustomTask<>(c, task);
	}
	
}
