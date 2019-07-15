package com.wjz.demo.concurrent.executor.fixed;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Assert;
import org.junit.Test;

/**
 * 固定线程数的线程池
 * 
 * @author iss002
 *
 */
public class FixedThreadPoolTest {
	
	@Test
	public void fixed() {
		// 核心线程数和最大线程数相等，不会有空闲线程
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			executor.execute(new Task());
		}
		Assert.assertEquals(5, executor.getCorePoolSize());
	}

	class Task implements Runnable {

		@Override
		public void run() {
			
		}
		
	}
}
