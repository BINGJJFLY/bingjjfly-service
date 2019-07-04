package com.wjz.demo.concurrent.threadpool;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

public class ThreadPoolExecutorTest {

	private int corePoolSize;
	private int maximumPoolSize;
	private long keepAliveTime;
	private TimeUnit unit;
	private BlockingQueue<Runnable> workQueue;
	private ThreadFactory threadFactory;
	private RejectedExecutionHandler handler;

	@Before
	public void init() {
		corePoolSize = 5;
		maximumPoolSize = 10;
		keepAliveTime = 1000;
		unit = TimeUnit.MILLISECONDS;
		workQueue = new LinkedBlockingQueue<>(10);
		threadFactory = Executors.defaultThreadFactory();
		handler = new ThreadPoolExecutor.AbortPolicy();
	}

	@Test
	public void core() {
		CountDownLatch cdl = new CountDownLatch(5);
		ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory, handler);
		for (int i = 0; i < 5; i++) {
			pool.execute(new Task());
			cdl.countDown();
		}
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		CountDownLatch cdl = new CountDownLatch(10);
		ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 8, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1));
		for (int i = 0; i < 8; i++) {
			pool.execute(new Task());
			cdl.countDown();
		}
		for (int i = 0; i < 2; i++) {
			pool.execute(new Task());
			cdl.countDown();
		}
		
	}
	
	@Test
	public void allowCoreThreadTimeOut() {
		CountDownLatch cdl = new CountDownLatch(10);
		ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory, handler);
		// 设置为true后，任务队列中没有任务时，线程空闲，空闲指定时间后线程销毁
		pool.allowCoreThreadTimeOut(true);
		for (int i = 0; i < 8; i++) {
			pool.execute(new Task());
			cdl.countDown();
		}
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queue() {
		CountDownLatch cdl = new CountDownLatch(10);
		ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory, handler);
		// 创建核心线程执行任务，线程均在获取队列里的任务，队列里一旦有了任务就会被核心线程消费掉
		for (int i = 0; i < 5; i++) {
			pool.execute(new Task());
			cdl.countDown();
		}
		// 将任务放入队列，供核心线程获取执行
		for (int i = 0; i < 5; i++) {
			pool.execute(new Task());
			cdl.countDown();
		}
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void prestartAllCoreThreads() {
		CountDownLatch cdl = new CountDownLatch(10);
		ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory, handler);
		pool.prestartAllCoreThreads();
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void shutdown() {
		CountDownLatch cdl = new CountDownLatch(50);
		ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory, handler);
		for (int i = 0; i < 15; i++) {
			pool.execute(new Task());
			cdl.countDown();
		}
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			//
		}
		// 启动一个有序的关机，在此之前提交任务已执行，但不会接受任何新任务。如果已经关闭，则调用没有其他效果。
		pool.shutdown();
//		for (int i = 0; i < 5; i++) {
//			pool.execute(new Task());
//			cdl.countDown();
//		}
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void shutdownNow() {
		CountDownLatch cdl = new CountDownLatch(50);
		ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory, handler);
		for (int i = 0; i < 15; i++) {
			pool.execute(new Task());
			cdl.countDown();
		}
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			//
		}
		List<Runnable> tasks = pool.shutdownNow();
		System.out.println(tasks.size());
//		for (int i = 0; i < 5; i++) {
//			pool.execute(new Task());
//			cdl.countDown();
//		}
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void useSynchronousQueue() {
		CountDownLatch cdl = new CountDownLatch(50);
		// 当队列为SynchronousQueue时，因为其不能存储任务，所以任务被线程池容纳的最大值为最大线程数
		// org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor未指定队列容量时
		// 就会使用SynchronousQueue
		workQueue = new SynchronousQueue<Runnable>();
		ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory, handler);
		for (int i = 0; i < 10; i++) {
			pool.execute(new Task());
			cdl.countDown();
		}
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static class Task implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(3000);
				System.out.println(Thread.currentThread().getName());
			} catch (InterruptedException e) {
				System.out.println("线程被中断了");
			}
		}

	}

}
