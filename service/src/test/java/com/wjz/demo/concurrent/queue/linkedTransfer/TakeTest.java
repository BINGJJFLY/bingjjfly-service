package com.wjz.demo.concurrent.queue.linkedTransfer;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TakeTest {
	
	@Test
	public void take() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
//		queue.offer("hello");
//		queue.put("world");
//		queue.offer("bing");
//		queue.add("ping");
//		queue.offer("iss002", 100, TimeUnit.MILLISECONDS);
//		
		try {
			queue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void takeItem() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// 队列空时添加一个空节点到队列并阻塞
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
		queue.offer("hello");
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void poll() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		// 立即返回，不会阻塞
		queue.poll();
	}
	
	@Test
	public void pollNanos() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		try {
			// 队列空时添加一个空节点到队列并阻塞3000毫秒
			queue.poll(3000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
