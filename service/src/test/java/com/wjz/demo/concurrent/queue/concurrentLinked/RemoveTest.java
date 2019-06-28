package com.wjz.demo.concurrent.queue.concurrentLinked;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Test;

public class RemoveTest {
	
	@Test
	public void removeObj() {
		ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
		queue.offer("hello");
		queue.offer("world");
		queue.offer("bing");
		queue.offer("ping");
		queue.remove("world");
	}

}
