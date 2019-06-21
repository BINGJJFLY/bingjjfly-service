package com.wjz.demo.concurrent.queue.arrayBlocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class ClearTest {
	
	@Test
	public void clear() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
		queue.clear();
	}

	/*
	 public void clear() {
        final Object[] items = this.items;
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            int k = count;
            if (k > 0) {
                final int putIndex = this.putIndex;
                int i = takeIndex;
                do {
                    items[i] = null;
                    if (++i == items.length)
                        i = 0;
                } while (i != putIndex);
                takeIndex = putIndex;
                count = 0;
                if (itrs != null)
                    itrs.queueIsEmpty();
                
                $* 循环count次，只要是有那么多等待的线程，全部唤醒 *$
                for (; k > 0 && lock.hasWaiters(notFull); k--)
                    notFull.signal();
            }
        } finally {
            lock.unlock();
        }
    }
	 */
}
