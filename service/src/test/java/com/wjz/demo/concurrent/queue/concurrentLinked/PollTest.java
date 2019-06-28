package com.wjz.demo.concurrent.queue.concurrentLinked;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Test;

public class PollTest {
	
	@Test
	public void poll() {
		ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
		queue.poll();
		queue.offer("hello");
		queue.poll();
	}
	
	/*
	 public E poll() {
	 	$* 发现哨兵节点后从头节点开始重新遍历 *$
        restartFromHead:
        for (;;) {
        	$* head节点不一定就是链表的头节点 *$
            for (Node<E> h = head, p = h, q;;) {
                E item = p.item;
				$* 头节点元素不为空则CAS设置头节点元素为空 *$
                if (item != null && p.casItem(item, null)) {
                    // Successful CAS is the linearization point
                    // for item to be removed from this queue.
                    $* head不是链表的头节点，每两次更新一次head *$
                    if (p != h) // hop two nodes at a time
                        updateHead(h, ((q = p.next) != null) ? q : p);
                    return item;
                }
                else if ((q = p.next) == null) {
                    updateHead(h, p);
                    return null;
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
