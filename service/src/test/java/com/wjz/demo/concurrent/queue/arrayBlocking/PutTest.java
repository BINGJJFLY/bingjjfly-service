package com.wjz.demo.concurrent.queue.arrayBlocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class PutTest {
	
	@Test
	public void put() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
		try {
			queue.put("hello");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void putAndWait() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
				try {
					queue.put("hello");
					queue.put("world");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		try {
			t.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	/*
	 public void put(E e) throws InterruptedException {
        checkNotNull(e);
        final ReentrantLock lock = this.lock;
        $* 加锁，响应中断 *$
        lock.lockInterruptibly();
        try {
            while (count == items.length)
                notFull.await();
            enqueue(e);
        } finally {
            lock.unlock();
        }
     }
	 */
}
