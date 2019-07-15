package com.wjz.demo.concurrent.executor.single;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

public class SingleThreadPoolExecutorTest {
	
	@Test
	public void single() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
			executor.execute(new Task());
		}
	}
	
	@Test
	public void test() {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
		for (int i = 0; i < 10; i++) {
			executor.execute(new Task());
		}
		Assert.assertEquals(1, executor.getActiveCount());
	}
	
	class Task implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
