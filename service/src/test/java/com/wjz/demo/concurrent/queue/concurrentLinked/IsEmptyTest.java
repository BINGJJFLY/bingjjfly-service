package com.wjz.demo.concurrent.queue.concurrentLinked;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Test;

public class IsEmptyTest {
	
	@Test
	public void isEmpty() {
		ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
		queue.isEmpty();
		queue.offer("hello");
		queue.isEmpty();
	}

	/*
	 Node<E> first() {
        restartFromHead:
        for (;;) {
            for (Node<E> h = head, p = h, q;;) {
                boolean hasItem = (p.item != null);
                if (hasItem || (q = p.next) == null) {
                    updateHead(h, p);
                    return hasItem ? p : null;
                }
                else if (p == q)
                    continue restartFromHead;
                else
                    p = q;
            }
        }
    }
	 */
}
