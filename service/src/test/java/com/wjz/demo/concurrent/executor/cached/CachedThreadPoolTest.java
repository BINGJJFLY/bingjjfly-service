package com.wjz.demo.concurrent.executor.cached;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author iss002
 *
 */
public class CachedThreadPoolTest {

	@Test
	public void test() {
		ThreadPoolExecutor fastExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			// 执行的任务较快的线程再次执行任务而不是新创建线程
			fastExecutor.execute(new FastTask());
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(true, fastExecutor.getActiveCount() < 100);
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			// 任务执行很慢，每次执行任务都会新创建一个线程
			executor.execute(new SlowTask());
		}
		Assert.assertEquals(100, executor.getActiveCount());
	}
	
	class FastTask implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep((int)(Math.random()*1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	class SlowTask implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
