package com.wjz.demo.concurrent.queue.linkedBlocking;

import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Assert;
import org.junit.Test;

public class RemoveTest {
	
	@Test
	public void removeObject() {
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
		queue.offer("hello");
		queue.offer("world");
		queue.offer("bing");
		Assert.assertEquals(true, queue.remove("world"));
	}

	/*
	 public boolean remove(Object o) {
        if (o == null) return false;
        $* 加两把锁 *$
        fullyLock();
        try {
        	$* 链表结构中头结点的item始终为null，trail表示追踪即当前节点，p表示当前节点的下一个节点即第一个有值的节点 *$
            for (Node<E> trail = head, p = trail.next;
                 p != null;
                 $* 循环完之后，trail赋值为当前节点的下一个节点，p赋值为当前节点的下一个节点的下一个节点 *$
                 trail = p, p = p.next) {
                if (o.equals(p.item)) {
                	$* 将符合条件的第一个的节点从链表中删除 *$
                    unlink(p, trail);
                    return true;
                }
            }
            return false;
        } finally {
            fullyUnlock();
        }
    }
	 */
	
	/*
	 void unlink(Node<E> p, Node<E> trail) {
        // assert isFullyLocked();
        // p.next is not changed, to allow iterators that are
        // traversing p to maintain their weak-consistency guarantee.
        $* 符合条件的节点值置为null *$
        p.item = null;
        $* 当前节点的下一个节点置为符合条件的节点的下一个节点 *$
        trail.next = p.next;
        $* 符合条件的节点为链表尾节点 *$
        if (last == p)
        	$* 尾节点置为当前节点 *$
            last = trail;
        if (count.getAndDecrement() == capacity)
            notFull.signal();
    }
	 */
}
