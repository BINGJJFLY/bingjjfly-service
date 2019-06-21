package com.wjz.demo.concurrent.queue.arrayBlocking;

import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Assert;
import org.junit.Test;

public class TakeTest {
	
	@Test
	public void take() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
		queue.offer("hello");
		try {
			// 数组为空时线程等待
			Assert.assertEquals("hello", queue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void takeAndWait() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
				try {
					// 数组为空时线程等待
					queue.take();
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
	 public E take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == 0)
                notEmpty.await();
            return dequeue();
        } finally {
            lock.unlock();
        }
    }
	 */

}
