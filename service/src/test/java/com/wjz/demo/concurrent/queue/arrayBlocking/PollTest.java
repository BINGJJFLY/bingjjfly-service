package com.wjz.demo.concurrent.queue.arrayBlocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Assert;
import org.junit.Test;

public class PollTest {
	
	@Test
	public void poll() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
		// 返回null或者是数组的元素
		Assert.assertEquals(null, queue.poll());
		queue.offer("hello");
		queue.offer("world");
		queue.offer("bing");
		Assert.assertEquals("hello", queue.poll());
	}

	/*
	 public E poll() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
        	$* 数组为空时返回null *$
            return (count == 0) ? null : dequeue();
        } finally {
            lock.unlock();
        }
    }
	 */
	
	/*
	 private E dequeue() {
        // assert lock.getHoldCount() == 1;
        // assert items[takeIndex] != null;
        final Object[] items = this.items;
        @SuppressWarnings("unchecked")
        E x = (E) items[takeIndex];
        items[takeIndex] = null;
        if (++takeIndex == items.length)
            takeIndex = 0;
        count--;
        if (itrs != null)
            itrs.elementDequeued();
        notFull.signal();
        return x;
    }
	 */
	
	@Test
	public void pollNanos() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
		try {
			queue.poll(100, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
        	$* 当数组空时，等待时长小于等于0时返回null，大于0时阻塞线程时间为传入的时长 *$
        	$* 过期之后线程再次竞争锁，获得锁后继续判断数组是否为空 *$
            while (count == 0) {
                if (nanos <= 0)
                    return null;
                nanos = notEmpty.awaitNanos(nanos);
            }
            return dequeue();
        } finally {
            lock.unlock();
        }
    }
	 */
}
