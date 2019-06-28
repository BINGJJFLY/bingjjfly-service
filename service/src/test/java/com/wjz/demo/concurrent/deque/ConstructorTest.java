package com.wjz.demo.concurrent.deque;

import java.util.concurrent.LinkedBlockingDeque;

import org.junit.Test;

/**
 * 双端链表阻塞队列
 * 入队出队操作采用独占锁方式，并发性低
 * LinkedBlockingDeque和LinkedBlockingQueue比较后者采用出队锁和入队锁并发性稍高
 * LinkedBlockingDeque均可以从头部或尾部入队和出队，LinkedBlockingQueue只能从头部出队从尾部入队
 *
 * @author iss002
 *
 */
public class ConstructorTest {

	@Test
	public void constructor() {
		// 默认链表容量Integer.MAX_VALUE
		LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>();
		// 指定容量
		LinkedBlockingDeque<String> deque_2= new LinkedBlockingDeque<>(10);
	}
}
