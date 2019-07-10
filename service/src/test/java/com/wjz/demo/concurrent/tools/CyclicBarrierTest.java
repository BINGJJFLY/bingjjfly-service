package com.wjz.demo.concurrent.tools;

import java.util.concurrent.CyclicBarrier;

import org.junit.Test;

/**
 * 同步屏障，适用于多线程执行子任务，子任务全部执行完后做汇总
 *
 * @author iss002
 *
 */
public class CyclicBarrierTest {
	
	@Test
	public void test() {
		CyclicBarrier cb = new CyclicBarrier(2);
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// 子线程先到达的话阻塞，等待Main线程
					cb.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 全部线程都到达同步屏障时，同时执行
				System.out.println("1");
			}
		});
		t.start();
		
		try {
			// Main线程先到达的话阻塞，等待子线程
			cb.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 全部线程都到达同步屏障时，同时执行
		System.out.println("2");
	}
	
	@Test
	public void test2() {
		// 全部线程都到达同步屏障时优先执行BarrierAction中的任务
		CyclicBarrier cb = new CyclicBarrier(2, new BarrierAction());
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// 子线程先到达的话阻塞，等待Main线程
					cb.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 全部线程都到达同步屏障时，同时执行
				System.out.println("1");
			}
		});
		t.start();
		
		try {
			// Main线程先到达的话阻塞，等待子线程
			cb.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 全部线程都到达同步屏障时，同时执行
		System.out.println("2");
	}
	
	class BarrierAction implements Runnable {

		@Override
		public void run() {
			System.out.println("3");
		}
		
	}
}
