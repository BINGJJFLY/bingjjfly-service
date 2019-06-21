package com.wjz.demo.concurrent.queue.arrayBlocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class ContainsTest {
	
	@Test
	public void contains() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
		queue.contains("hello");
	}
	
	/*
	 public boolean contains(Object o) {
        if (o == null) return false;
        final Object[] items = this.items;
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            if (count > 0) {
                final int putIndex = this.putIndex;
                int i = takeIndex;
                do {
                    if (o.equals(items[i]))
                        return true;
                    if (++i == items.length)
                        i = 0;
                } while (i != putIndex);
            }
            return false;
        } finally {
            lock.unlock();
        }
    }
	 */

}
