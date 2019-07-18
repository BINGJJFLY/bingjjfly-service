package com.wjz.demo.concurrent.executor.scheduled;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ScheduledThreadPoolExecutorTest {

	@Test
	public void scheduled() {
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
		executor.schedule(new Task(), 500, TimeUnit.MILLISECONDS);
	}
	
	class Task implements Runnable {

		@Override
		public void run() {
			
		}
		
	}
}
