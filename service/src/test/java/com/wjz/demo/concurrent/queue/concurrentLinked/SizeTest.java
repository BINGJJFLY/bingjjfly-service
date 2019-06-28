package com.wjz.demo.concurrent.queue.concurrentLinked;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Test;

public class SizeTest {
	
	@Test
	public void size() {
		ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
		// 链表中有两个节点，head节点无元素，size应该为1
		queue.offer("hello");
		queue.offer("world");
		// 更新头节点
		queue.size();
		queue.offer("world");
		queue.size();
	}

	/*
	 public int size() {
        int count = 0;
        for (Node<E> p = first(); p != null; p = succ(p))
            if (p.item != null)
                // Collection.size() spec says to max out
                if (++count == Integer.MAX_VALUE)
                    break;
        return count;
    }
	 */
}
