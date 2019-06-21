package com.wjz.demo.concurrent.queue.arrayBlocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Assert;
import org.junit.Test;

public class RemainingCapacityTest {
	
	@Test
	public void remainingCapacity() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
		Assert.assertEquals(10, queue.remainingCapacity());
		queue.offer("hello");
		Assert.assertEquals(9, queue.remainingCapacity());
	}
	
	/*
	 public int remainingCapacity() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return items.length - count;
        } finally {
            lock.unlock();
        }
    }
	 */
	
}
