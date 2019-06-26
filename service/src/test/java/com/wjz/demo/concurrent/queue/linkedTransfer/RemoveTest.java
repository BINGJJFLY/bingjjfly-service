package com.wjz.demo.concurrent.queue.linkedTransfer;

import java.util.NoSuchElementException;
import java.util.concurrent.LinkedTransferQueue;

import org.junit.Assert;
import org.junit.Test;

public class RemoveTest {
	
	@Test(expected = NoSuchElementException.class)
	public void remove() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		queue.remove();
	}
	
	@Test
	public void removeObj() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		Assert.assertEquals(false, queue.remove("hello"));
		queue.offer("hello");
		Assert.assertEquals(true, queue.remove("hello"));
		queue.offer("world");
		Assert.assertEquals(true, queue.remove("world"));
		queue.offer("bing");
		Assert.assertEquals(true, queue.remove("bing"));
		
//		for (int i = 0; i < 29; i++) {
//			queue.offer("Index-" + i);
//			Assert.assertEquals(true, queue.remove("Index-" + i));
//		}
//		queue.offer("ping");
//		queue.remove("ping");
	}

}
