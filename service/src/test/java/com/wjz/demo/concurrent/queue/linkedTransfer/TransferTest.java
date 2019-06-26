package com.wjz.demo.concurrent.queue.linkedTransfer;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;

public class TransferTest {
	
	@Test
	public void transfer() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		try {
			// 添加元素后如果没有取走则等待
			queue.transfer("hello");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void transferAsync() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		final AtomicInteger count = new AtomicInteger();
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						String s = "H-"+count.getAndIncrement();
						queue.transfer(s);
						System.out.println("元素["+s+"]被取走了");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			t.start();
		}
		try {
			String r;
			while ((r = queue.take()) != null) {
				System.out.println("获取到元素["+r+"]");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void transferAndTake() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// 添加元素后如果没有取走则阻塞
					queue.transfer("hello");
					System.out.println("[hello]元素被取走了");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		try {
			String item = queue.take();
			System.out.println("获取到元素["+item+"]");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void tryTransfer() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		// 如果可能，立即将添加元素转移给等待的线程
		// 如果存在线程获取元素时阻塞（take()或poll(long,TimeUnit)）中，则立即传送指定的元素，否则返回 false
		Assert.assertEquals(false, queue.tryTransfer("hello"));
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String item = queue.take();
					System.out.println("获取到元素["+item+"]");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		while (!queue.tryTransfer("world"));
		System.out.println("转移给了获取阻塞线程元素[world]");
	}
}
