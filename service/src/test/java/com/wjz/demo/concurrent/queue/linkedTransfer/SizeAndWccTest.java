package com.wjz.demo.concurrent.queue.linkedTransfer;

import java.util.concurrent.LinkedTransferQueue;

import org.junit.Assert;
import org.junit.Test;

public class SizeAndWccTest {
	
	@Test
	public void size() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		Assert.assertEquals(0, queue.size());
		queue.offer("hello");
		Assert.assertEquals(1, queue.size());
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					queue.transfer("world");
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
		Assert.assertEquals(2, queue.size());
	}
	
	@Test
	public void takeSize() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		queue.offer("hello");
		try {
			queue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(0, queue.size());
		queue.offer("world");
		Assert.assertEquals(1, queue.size());
	}
	
	@Test
	public void wcc() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		Assert.assertEquals(0, queue.getWaitingConsumerCount());
		// 立即返回不会添加获取节点，所以消费者等待线程为0
		queue.poll();
		Assert.assertEquals(0, queue.getWaitingConsumerCount());
		queue.offer("hello");
		Assert.assertEquals(0, queue.getWaitingConsumerCount());
		queue.poll();
		Assert.assertEquals(0, queue.getWaitingConsumerCount());
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
		// 查询等待的消费者线程个数
		Assert.assertEquals(1, queue.getWaitingConsumerCount());
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						queue.take();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(4, queue.getWaitingConsumerCount());
		queue.poll();
		Assert.assertEquals(4, queue.getWaitingConsumerCount());
	}

	/*
	 private int countOfMode(boolean data) {
        int count = 0;
        for (Node p = head; p != null; ) {
        	$* 节点是否已经匹配，取未匹配的 *$
            if (!p.isMatched()) {
            	$* size传入的data为true即添加的节点且未匹配的，offer、put、add、transfer等 *$
            	$* getWaitingConsumerCount传入的data为false，即获取的节点且未匹配的，take、poll(long, unit)等 *$
                if (p.isData != data)
                    return 0;
                if (++count == Integer.MAX_VALUE) // saturated
                    break;
            }
            Node n = p.next;
            if (n != p)
                p = n;
            else {
                count = 0;
                p = head;
            }
        }
        return count;
    }
	 */
	
	/*
	 public boolean hasWaitingConsumer() {
        return firstOfMode(false) != null;
    }
	 */
}
