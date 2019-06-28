package com.wjz.demo.concurrent.queue.concurrentLinked;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Test;

/**
 * 获取链表头节点（第一个元素不为空的节点）元素，但不移除头节点
 *
 * @author iss002
 *
 */
public class PeekTest {
	
	@Test
	public void peek() {
		ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
		queue.peek();
		queue.offer("hello");
		// 更新了一次头节点
		queue.peek();
		// 未更新头节点
		queue.peek();
	}

	/*
	 public E peek() {
        restartFromHead:
        for (;;) {
            for (Node<E> h = head, p = h, q;;) {
                E item = p.item;
                $* 当前节点不为null或者是当前节点的下一个节点为null时，更新头节点为当前节点 *$
                if (item != null || (q = p.next) == null) {
                    updateHead(h, p);
                    return item;
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
