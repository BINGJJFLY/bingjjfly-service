package com.wjz.demo.java.list.linkedlist;

import java.util.LinkedList;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;

/**
 * LinkedList可分割迭代器
 * 
 * @author iss002
 *
 */
public class SpliteratorTest {

	@Test
	public void advance() {
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		Spliterator<Integer> sit = list.spliterator();
		boolean hasMore = true;
		int i = 0;

		while (hasMore) {
			hasMore = sit.tryAdvance(System.out::println);
			if (hasMore) {
				System.out.println("---------------------------");
			}
			i++;
			if (i == 5) {
				hasMore = false;
			}
		}
		sit.forEachRemaining(System.out::println);
	}

	@Before
	public void init() {
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
	}

	private LinkedList<Integer> list = new LinkedList<>();
	private volatile Spliterator<Integer> spliter = list.spliterator();
	private final Object lock = new Object();
	private AtomicInteger count = new AtomicInteger(0);

	@Test
	public void split() {
		for (int i = 0; i < 5; i++) {
			try {
				SplitThread t = new SplitThread();
				t.start();
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("运行结果：" + count.intValue());
	}

	private Spliterator<Integer> trySplit() {
		synchronized (lock) {
			if (spliter != null) {
				return spliter.trySplit();
			}
			return null;
		}
	}

	private class SplitThread extends Thread {
		@Override
		public void run() {
			Spliterator<Integer> split = trySplit();
			if (split != null) {
				split.forEachRemaining(new Consumer<Integer>() {
					@Override
					public void accept(Integer i) {
						count.addAndGet(i);
					}
				});
			}
		}
	}
}
