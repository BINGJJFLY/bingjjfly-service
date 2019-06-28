package com.wjz.demo.concurrent.queue.concurrentLinked;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Test;

/**
 * 单向无界非阻塞链表队列
 * 全局无锁操作，使用CAS操作保证高效和安全
 *
 * @author iss002
 *
 */
public class ConstructorTest {

	@Test
	public void constructor() {
		// 初始化头节点和尾节点
		ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
	}
	
	/*
	 public ConcurrentLinkedQueue() {
        head = tail = new Node<E>(null);
    }
	 */
}
