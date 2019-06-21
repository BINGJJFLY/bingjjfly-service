package com.wjz.demo.concurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class FairAndUnfairTest {

	ReentrantLock fairLock = new AscReentrantLock(true);
	ReentrantLock unfairLock = new AscReentrantLock(false);
	CountDownLatch countDownLatch = new CountDownLatch(1);

	@Test
	public void fair() {
		start(fairLock);
	}

	@Test
	public void unfair() {
		start(unfairLock);
	}
	
	@Test
	public void bit() {
		// 0000 0000 0000 0000 0000 0000 0000 0000 = 0 (无读锁，无写锁)
		// 0000 0000 0000 0000 0000 0000 0000 0001 = 1 (无读锁，1写锁)
		// 0000 0000 0000 0000 0000 0000 0000 0011 = 3 (无读锁，1写锁:2重进写锁)
		// 0000 0000 0000 0001 0000 0000 0000 0000 = 65536 (1读锁，无写锁)
		// 0000 0000 0000 0011 0000 0000 0000 0000 = 196608 (3读锁，无写锁)
		// 0000 0000 0000 0011 0000 0000 0000 0010 = 196610 (1写锁:1重进写锁:3获取读锁)
		
		int SHARED_SHIFT = 16;
		int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;
		// 写线程
		System.out.println("写线程：" + (196610 & EXCLUSIVE_MASK));
		// 读线程
		System.out.println("读线程：" + (196610 >>> 16));
		// 写线程+1
		System.out.println("写线程加1：" + (196610 + 1) + " 写线程：" + ((196610 + 1) & EXCLUSIVE_MASK));
		// 读线程+1
		System.out.println("读线程加1：" + (196610 + (1 << 16)) + " 读线程：" + ((196610 + (1 << 16)) >>> 16));
	}
	
	@Test
	public void switcher() {
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					
				}
			}, i + "");
			t.start();
		}
	}

	public void start(Lock lock) {
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(new Job(lock), i + "");
			t.start();
		}
		countDownLatch.countDown();
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class Job implements Runnable {
		Lock lock;

		public Job(Lock lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			try {
				countDownLatch.await();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			for (int i = 0; i < 3; i++) {
				lock.lock();
				try {
					System.out.println("Lock by [" + Thread.currentThread().getName() + "]， Waiting by "
							+ ((AscReentrantLock) lock).getQueuedThreads());
					Thread.sleep(200);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}

	}

	private class AscReentrantLock extends ReentrantLock {

		private static final long serialVersionUID = 8361513770221441204L;

		public AscReentrantLock(boolean fair) {
			super(fair);
		}

		@Override
		protected Collection<Thread> getQueuedThreads() {
			List<Thread> list = new ArrayList<>(super.getQueuedThreads());
			Collections.reverse(list);
			return list;
		}
	}
}
