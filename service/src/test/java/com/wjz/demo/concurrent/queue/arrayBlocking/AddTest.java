package com.wjz.demo.concurrent.queue.arrayBlocking;

import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Assert;
import org.junit.Test;

public class AddTest {

	@Test
	public void add() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
		// 调用offer(e)返回true时返回true，返回false时抛出异常（数组已满）
		Assert.assertEquals(true, queue.add("hello"));
	}

	@Test(expected = IllegalStateException.class)
	public void addAndThrowException() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
		// 调用offer(e)返回true时返回true，返回false时抛出异常（数组已满）
		Assert.assertEquals(true, queue.add("hello"));
		// 数组已满抛出异常
		queue.add("hello");
	}
}
