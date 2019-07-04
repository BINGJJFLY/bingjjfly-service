package com.wjz.demo.concurrent.queue.synchronous;

import java.util.concurrent.SynchronousQueue;

import org.junit.Test;

/**
 * 不存储元素的阻塞队列（添加元素、获取元素无锁操作），每个put必须等待一个take，反之亦然
 *
 * @author iss002
 *
 */
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
