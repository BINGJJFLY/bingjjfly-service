package com.wjz.demo.concurrent.queue.arrayBlocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Assert;
import org.junit.Test;

public class SizeTest {

	@Test
	public void size() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
		Assert.assertEquals(0, queue.size());
		queue.offer("hello");
		Assert.assertEquals(1, queue.size());
	}
	
	/*
	 public int size() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
	 */
}
