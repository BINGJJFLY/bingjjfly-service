package com.wjz.demo.concurrent.tools;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class CountDownLatchTest {
	
	CountDownLatch cdl = new CountDownLatch(2);
	
	@Test
	public void test() {
		for (int i = 0; i < 2; i++) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						// do something
					} finally {
						cdl.countDown();
					}
				}
			});
			t.start();
		}
		try {
			// 会阻塞Main线程，等待子线程完成任务之后才放行Main线程
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// print result
	}

}
