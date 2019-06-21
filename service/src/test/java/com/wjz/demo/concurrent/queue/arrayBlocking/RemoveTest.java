package com.wjz.demo.concurrent.queue.arrayBlocking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Assert;
import org.junit.Test;

public class RemoveTest {
	
	@Test
	public void remove() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
		queue.offer("hello");
		// 数组为空时抛出异常
		Assert.assertEquals("hello", queue.remove());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void removeAndThrowException() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
		// 数组为空时抛出异常
		queue.remove();
	}

	/*
	 public E remove() {
        E x = poll();
        if (x != null)
            return x;
        else
            throw new NoSuchElementException();
    }
	 */
	
	@Test
	public void removeObject() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
		Assert.assertEquals(false, queue.remove("hello"));
		queue.offer("hello");
		queue.offer("world");
		queue.offer("java");
		queue.offer("iss002");
		queue.offer("bing");
		queue.poll();
		Assert.assertEquals(true, queue.remove("java"));
	}
	
	/*
	 public boolean remove(Object o) {
        if (o == null) return false;
        final Object[] items = this.items;
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
        	$* 数组不为空时 *$
            if (count > 0) {
                final int putIndex = this.putIndex;
                int i = takeIndex;
                do {
                    if (o.equals(items[i])) {
                        removeAt(i);
                        return true;
                    }
                    if (++i == items.length)
                        i = 0;
                } while (i != putIndex); 
            }
            return false;
        } finally {
            lock.unlock();
        }
    }
	 */
	
	@Test
	public void removeAll() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
		queue.offer("hello");
		queue.offer("world");
		Assert.assertEquals(2, queue.size());
		Collection<String> c = new ArrayList<>();
		c.add("hello");
		c.add("world");
		Assert.assertEquals(true, queue.removeAll(c));
		Assert.assertEquals(0, queue.size());
	}
}
