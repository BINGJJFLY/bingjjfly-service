package com.wjz.demo.concurrent.executor.cached;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class CachedThreadPoolTest {

	@Test
	public void test() {
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			executor.execute(new SlowTask());
		}
	}
	
	class SlowTask implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
