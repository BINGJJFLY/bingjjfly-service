package com.wjz.demo.concurrent.queue.synchronous;

import java.util.concurrent.SynchronousQueue;

import org.junit.Test;

public class OfferAndPutTest {
	
	@Test
	public void offer() {
		SynchronousQueue<String> queue = new SynchronousQueue<>();
		// 队列为空时不等待
		queue.offer("hello");
	}

	@Test
	public void put() {
		try {
			SynchronousQueue<String> queue = new SynchronousQueue<>();
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						queue.put("hello");
						Thread.sleep(3000);
						queue.put("world");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			t.start();
			Thread.sleep(1000);
			String result;
			while ((result = queue.take()) != null) {
				System.out.println(result);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
