package com.wjz.demo.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁/非公平锁上下文切换
 * Linux系统使用 vmstat 1 命令查看切换次数和时长
 *
 * @author iss002
 *
 */
public class SwitchTest {
	
	public static void main(String[] args) {
		switching(true);
	}

	public static void switching(boolean fair) {
		ReentrantLock lock = new ReentrantLock(fair);
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 100000; j++) {
						lock.lock();
						try {
						
						} finally {
							lock.unlock();
						}
					}
				}
			},  i + "");
			t.start();
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
