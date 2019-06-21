package com.wjz.demo.concurrent.queue.linkedBlocking;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class OfferTest {
	
	@Test
	public void offer() {
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
		queue.offer("hello");
		queue.offer("world");
	}

	/*
	 public boolean offer(E e) {
        if (e == null) throw new NullPointerException();
        final AtomicInteger count = this.count;
        $* 链表长度已经达到上限容量 *$
        if (count.get() == capacity)
            return false;
        int c = -1;
        Node<E> node = new Node<E>(e);
        final ReentrantLock putLock = this.putLock;
        putLock.lock();
        try {
        	$* 链表长度还未达到上限容量 *$
            if (count.get() < capacity) {
                enqueue(node);
                $* 先获得原值再原值加1，返回的是原值 *$
                c = count.getAndIncrement();
                $* 链表长度还未达到上限容量 *$
                if (c + 1 < capacity)
                	$* 因为加锁是putLock所以只能用notFull锁条件（putLock的锁条件） *$
                    notFull.signal();
            }
        } finally {
            putLock.unlock();
        }
        if (c == 0)
        	$* 加获取锁，唤醒不为空锁条件的等待线程 *$
            signalNotEmpty();
        return c >= 0;
    }
	 */
	
	/*
	 private void signalNotEmpty() {
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }
	 */
	
	@Test
	public void offerNanos() {
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
		try {
			queue.offer("hello", 100, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 public boolean offer(E e, long timeout, TimeUnit unit)
        throws InterruptedException {

        if (e == null) throw new NullPointerException();
        long nanos = unit.toNanos(timeout);
        int c = -1;
        final ReentrantLock putLock = this.putLock;
        final AtomicInteger count = this.count;
        putLock.lockInterruptibly();
        try {
        	$* 当链表的长度达到容量大小时，notFull锁条件等待，等待时长为传入的时长 *$
            while (count.get() == capacity) {
                if (nanos <= 0)
                    return false;
                nanos = notFull.awaitNanos(nanos);
            }
            enqueue(new Node<E>(e));
            c = count.getAndIncrement();
            $* 链表长度还未达到上限容量 *$
            if (c + 1 < capacity)
                notFull.signal();
        } finally {
            putLock.unlock();
        }
        if (c == 0)
            signalNotEmpty();
        return true;
    }
	 */
}
