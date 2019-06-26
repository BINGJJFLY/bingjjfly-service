package com.wjz.demo.concurrent.queue.linkedBlocking;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

public class PollTest {
	
	@Test
	public void poll() {
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
		queue.offer("hello");
		queue.offer("world");
		queue.offer("bing");
		Assert.assertEquals("hello", queue.poll());
	}

	/*
	 public E poll() {
        final AtomicInteger count = this.count;
        $* 链表上无节点 *$
        if (count.get() == 0)
            return null;
        E x = null;
        int c = -1;
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        try {
            if (count.get() > 0) {
                x = dequeue();
                $* 方法执行完后count减1 *$
                c = count.getAndDecrement();
                if (c > 1)
                	$* 因为加锁是takeLock所以只能用notEmpty锁条件（takeLock的锁条件） *$
                    notEmpty.signal();
            }
        } finally {
            takeLock.unlock();
        }
        if (c == capacity)
        	$* 加添加锁，唤醒不满锁条件的等待线程（队列已满但是刚刚删除了一个节点） *$
            signalNotFull();
        return x;
    }
	 */
	
	/*
	 private E dequeue() {
        // assert takeLock.isHeldByCurrentThread();
        // assert head.item == null;
        Node<E> h = head;
        $* 头结点的下一个节点 *$
        Node<E> first = h.next;
        h.next = h; // help GC
        $* 链表删除头结点 *$
        head = first;
        E x = first.item;
        first.item = null;
        return x;
    }
	 */
	
	@Test
	public void offerNanos() {
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
		queue.offer("hello");
		queue.offer("world");
		queue.offer("bing");
		try {
			queue.poll(100, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        E x = null;
        int c = -1;
        long nanos = unit.toNanos(timeout);
        final AtomicInteger count = this.count;
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lockInterruptibly();
        try {
        	$* 链表为空 *$
            while (count.get() == 0) {
                if (nanos <= 0)
                    return null;
                nanos = notEmpty.awaitNanos(nanos);
            }
            x = dequeue();
            c = count.getAndDecrement();
            if (c > 1)
                notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
        if (c == capacity)
            signalNotFull();
        return x;
    }
	 */
}
