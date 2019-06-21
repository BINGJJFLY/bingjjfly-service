package com.wjz.demo.concurrent;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
	
	public static void main(String[] args) {
		try {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("running");
				}
			}, "park");
			t.start();
			// 只有调用LockSupport.unpark(Thread t)方法或者是当前线程被中断才能从park()方法返回
			LockSupport.park(t);
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
