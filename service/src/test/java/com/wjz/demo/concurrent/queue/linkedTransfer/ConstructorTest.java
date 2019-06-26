package com.wjz.demo.concurrent.queue.linkedTransfer;

import java.util.concurrent.LinkedTransferQueue;

import org.junit.Test;

/**
 * 链表无界阻塞队列（即可存储元素又无锁）
 * 相当于LinkedBlockingQueue（可存储元素）和SynchronousQueue（无锁）的进化超集
 *
 * @author iss002
 *
 */
public class ConstructorTest {
	
	@Test
	public void constructor() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		LinkedTransferQueue<String> queue_2 = new LinkedTransferQueue<>(queue);
	}

}
