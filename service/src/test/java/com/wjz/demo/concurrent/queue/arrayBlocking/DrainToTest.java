package com.wjz.demo.concurrent.queue.arrayBlocking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Assert;
import org.junit.Test;

public class DrainToTest {
	
	@Test
	public void drainTo() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
		queue.offer("hello");
		queue.offer("world");
		List<String> c = new ArrayList<>();
		// 清空队列并唤醒等待填充队列的线程，填充集合，
		Assert.assertEquals(2, queue.drainTo(c));
		Assert.assertEquals(0, queue.size());
		Assert.assertEquals(2, c.size());
	}

}
