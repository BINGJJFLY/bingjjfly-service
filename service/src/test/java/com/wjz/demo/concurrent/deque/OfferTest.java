package com.wjz.demo.concurrent.deque;

import java.util.concurrent.LinkedBlockingDeque;

import org.junit.Test;

public class OfferTest {
	
	@Test
	public void offer() {
		LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>();
		// 默认在链表尾部添加节点
		deque.offer("hello");
		deque.offerLast("world");
		deque.offer("bing");
		deque.offerFirst("ping");
	}

}
