package com.wjz.demo.concurrent.queue.arrayBlocking;

import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Assert;
import org.junit.Test;

public class PeekTest {
	
	@Test
	public void peek() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
		// 偷偷看一眼数组是否为空
		Assert.assertEquals(null, queue.peek());
		queue.offer("hello");
		Assert.assertEquals("hello", queue.peek());
	}

	/*
	 public E peek() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return itemAt(takeIndex); // null when queue is empty
        } finally {
            lock.unlock();
        }
    }
	 */
	
	/*
	 final E itemAt(int i) {
        return (E) items[i];
    }
	 */
}
