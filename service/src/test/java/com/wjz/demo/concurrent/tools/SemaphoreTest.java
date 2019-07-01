package com.wjz.demo.concurrent.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量，类似于TwinsLock
 * 适用于只允许指定线程数的线程并发执行
 *
 * @author iss002
 *
 */
public class SemaphoreTest {
	
	private static final int THREAD_COUNT = 30;
	private static final ExecutorService POOL = Executors.newFixedThreadPool(THREAD_COUNT);
	// 假设数据库连接只有10个
	private static final Semaphore semaphore = new Semaphore(10);
	
	public static void main(String[] args) {
		for (int i = 0; i < THREAD_COUNT; i++) {
			POOL.execute(new Runnable() {
				@Override
				public void run() {
					try {
						semaphore.acquire();
						// do something
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						semaphore.release();
					}
				}
			});
			POOL.shutdown();
		}
	}
}
