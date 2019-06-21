package com.wjz.demo.concurrent.queue.arrayBlocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Assert;
import org.junit.Test;

public class OfferTest {
	
	@Test
	public void offer() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
		// 添加元素返回true或者是false
		Assert.assertEquals(true, queue.offer("hello"));
	}
	
	@Test
	public void offerAndReturnFalse() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
		// 添加元素返回true或者是false
		Assert.assertEquals(true, queue.offer("hello"));
		Assert.assertEquals(false, queue.offer("world"));
	}

	/*
	 public boolean offer(E e) {
        checkNotNull(e);
        final ReentrantLock lock = this.lock;
        $* 互斥性竞争锁 *$
        lock.lock();
        try {
        	$* 数组元素已满 *$
            if (count == items.length)
                return false;
            $* 数组元素未满，最终需要唤醒等待在notEmpty锁条件上的线程 *$
            else {
                enqueue(e);
                return true;
            }
        } finally {
            lock.unlock();
        }
     }
	 */
	
	/*
	 private void enqueue(E x) {
        // assert lock.getHoldCount() == 1;
        // assert items[putIndex] == null;
        final Object[] items = this.items;
        items[putIndex] = x;
        if (++putIndex == items.length)
            putIndex = 0;
        count++;
        notEmpty.signal();
     }
	 */
	
	@Test
	public void offerNanos() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
		try {
			queue.offer("hello", 100, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 public boolean offer(E e, long timeout, TimeUnit unit)
        throws InterruptedException {

        checkNotNull(e);
        long nanos = unit.toNanos(timeout);
        final ReentrantLock lock = this.lock;
        $* 加锁，响应中断 *$
        lock.lockInterruptibly();
        try {
        	$* 当数组满时，等待时长小于等于0时返回false，大于0时阻塞线程时间为传入的时长 *$
        	$* 过期之后线程再次竞争锁，获得锁后继续判断数组是否已满 *$
            while (count == items.length) {
                if (nanos <= 0)
                    return false;
                nanos = notFull.awaitNanos(nanos);
            }
            enqueue(e);
            return true;
        } finally {
            lock.unlock();
        }
     }
	 */
}
