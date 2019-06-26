package com.wjz.demo.concurrent.queue.linkedTransfer;

import java.util.concurrent.LinkedTransferQueue;

import org.junit.Assert;
import org.junit.Test;

public class IsEmptyTest {
	
	@Test
	public void isEmpty() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		queue.isEmpty();
	}

	@Test
	public void takeIsEmpty() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					queue.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(true, queue.isEmpty());
	}
	
	@Test
	public void offerAndPollIsEmpty() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		queue.offer("hello");
		queue.poll();
		Assert.assertEquals(true, queue.isEmpty());
		queue.offer("world");
		Assert.assertEquals(false, queue.isEmpty());
	}
	
	/*
	 final boolean isMatched() {
            Object x = item;
            $* 添加类型item为null，获取类型item不为null则为匹配成功 *$
            return (x == this) || ((x == null) == isData);
        }
	 */
}
