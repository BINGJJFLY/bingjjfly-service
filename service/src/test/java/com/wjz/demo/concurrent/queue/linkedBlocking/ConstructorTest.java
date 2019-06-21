package com.wjz.demo.concurrent.queue.linkedBlocking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;

/**
 * 有界链表阻塞队列
 * 链表有头端和尾端，添加节点在尾端，删除节点在头端，所以可以有两个锁，一个添加锁一个删除锁
 *
 * @author iss002
 *
 */
public class ConstructorTest {
	
	@Test
	public void constructor() {
		// 默认容量为Integer.MAX_VALUE
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
	}

	@Test
	public void capacity() {
		// 指定容量
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
	}
	
	/*
	 public LinkedBlockingQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException();
        this.capacity = capacity;
        $* 初始化头结点和尾节点，头结点等于尾节点，头是整个链表，尾是链表的尾节点 *$
        last = head = new Node<E>(null);
    }
	 */
	
	@Test
	public void collection() {
		Collection<String> c = new ArrayList<>();
		c.add("hello");
		c.add("world");
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(c);
	}
	
	/*
	 public LinkedBlockingQueue(Collection<? extends E> c) {
        this(Integer.MAX_VALUE);
        final ReentrantLock putLock = this.putLock;
        putLock.lock(); // Never contended, but necessary for visibility
        try {
            int n = 0;
            for (E e : c) {
                if (e == null)
                    throw new NullPointerException();
                if (n == capacity)
                    throw new IllegalStateException("Queue full");
                enqueue(new Node<E>(e));
                ++n;
            }
            count.set(n);
        } finally {
            putLock.unlock();
        }
    }
	 */
	
	/*
	 private void enqueue(Node<E> node) {
        // assert putLock.isHeldByCurrentThread();
        // assert last.next == null;
        $* 相当于last.next = node; last = node; *$
        last = last.next = node;
    }
	 */
}
