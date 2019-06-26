package com.wjz.demo.concurrent.queue.linkedBlocking;

import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Assert;
import org.junit.Test;

public class ContainsTest {
	
	@Test
	public void contains() {
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
		queue.offer("hello");
		queue.offer("world");
		queue.offer("bing");
		Assert.assertEquals(true, queue.contains("hello"));
	}

	/*
	 public boolean contains(Object o) {
        if (o == null) return false;
        $* 调用此方法时不能再添加或删除节点，所以加添加锁和获取锁 *$
        fullyLock();
        try {
            for (Node<E> p = head.next; p != null; p = p.next)
                if (o.equals(p.item))
                    return true;
            return false;
        } finally {
            fullyUnlock();
        }
    }
	 */
	
	/*
	$* Locks to prevent both puts and takes. *$ 
	void fullyLock() {
        putLock.lock();
        takeLock.lock();
    }

    $* Unlocks to allow both puts and takes. *$
    void fullyUnlock() {
        takeLock.unlock();
        putLock.unlock();
    }
	 */
}
