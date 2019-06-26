package com.wjz.demo.concurrent.queue.linkedBlocking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Assert;
import org.junit.Test;

public class DrainToTest {
	
	@Test
	public void drainTo() {
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
		queue.offer("hello");
		queue.offer("world");
		queue.offer("bing");
		Assert.assertEquals(3, queue.size());
		Collection<String> c = new ArrayList<>();
		// 清空队列到集合中
		queue.drainTo(c);
		Assert.assertEquals(3, c.size());
	}

	/*
	 public int drainTo(Collection<? super E> c, int maxElements) {
        if (c == null)
            throw new NullPointerException();
        if (c == this)
            throw new IllegalArgumentException();
        if (maxElements <= 0)
            return 0;
        boolean signalNotFull = false;
        final ReentrantLock takeLock = this.takeLock;
        $* 链表添加节点是在尾部开始添加，获取节点是从头部开始获取，所以加获取锁，不加添加锁是因为更好的并发 *$
        takeLock.lock();
        try {
            int n = Math.min(maxElements, count.get());
            // count.get provides visibility to first n Nodes
            Node<E> h = head;
            int i = 0;
            try {
                while (i < n) {
                    Node<E> p = h.next;
                    c.add(p.item);
                    p.item = null;
                    h.next = h;
                    h = p;
                    ++i;
                }
                return n;
            } finally {
                // Restore invariants even if c.add() threw
                if (i > 0) {
                    // assert h.item == null;
                    head = h;
                    signalNotFull = (count.getAndAdd(-i) == capacity);
                }
            }
        } finally {
            takeLock.unlock();
            if (signalNotFull)
                signalNotFull();
        }
    }
	 */
}
