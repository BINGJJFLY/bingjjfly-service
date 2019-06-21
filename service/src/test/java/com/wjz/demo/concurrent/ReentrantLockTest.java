package com.wjz.demo.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
	
	static ReentrantLock lock = new ReentrantLock();
	
	public static void lock() throws InterruptedException {
		lock.lock();
		try {
			Thread.sleep(555000);
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						lock();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();;
		}
	}
}
