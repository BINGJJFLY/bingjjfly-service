package com.wjz.demo.concurrent.queue.linkedTransfer;

import java.util.concurrent.LinkedTransferQueue;

import org.junit.Assert;
import org.junit.Test;

public class PeekTest {
	
	@Test
	public void offerAndPeek() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		Assert.assertEquals(null, queue.peek());
		queue.offer("hello");
		queue.offer("world");
		Assert.assertEquals("hello", queue.peek());
	}

	@Test
	public void takeAndPeek() {
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
		Assert.assertEquals(null, queue.peek());
	}
	
	@Test
	public void transferAndPeek() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					queue.transfer("hello");
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
		Assert.assertEquals("hello", queue.peek());
	}
	
	/*
	 private E firstDataItem() {
        for (Node p = head; p != null; p = succ(p)) {
            Object item = p.item;
            $* 节点为添加类型，offer、put、transfer等 *$
            if (p.isData) {
                if (item != null && item != p)
                    return LinkedTransferQueue.<E>cast(item);
            }
            else if (item == null)
                return null;
        }
        return null;
    }
	 */
	
	/*
	 final Node succ(Node p) {
        Node next = p.next;
        return (p == next) ? head : next;
    }
	 */
}
