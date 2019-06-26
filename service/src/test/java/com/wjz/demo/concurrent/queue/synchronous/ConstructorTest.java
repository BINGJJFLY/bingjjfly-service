package com.wjz.demo.concurrent.queue.synchronous;

import java.util.concurrent.SynchronousQueue;

import org.junit.Test;

public class ConstructorTest {
	
	@Test
	public void constructor() {
		// 默认非公平锁
		SynchronousQueue<String> queue = new SynchronousQueue<>();
	}

	/*
	 public SynchronousQueue(boolean fair) {
	 	// 公平锁、非公平锁
        transferer = fair ? new TransferQueue<E>() : new TransferStack<E>();
    }
	 */
}
