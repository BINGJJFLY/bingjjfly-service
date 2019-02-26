package com.wjz.demo.java.list.arraylist;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import org.junit.Test;

/**
 * ArrayList可分割迭代器
 * 
 * @author iss002
 *
 */
public class SpliteratorTest {

	private AtomicInteger count = new AtomicInteger(0);
	private List<Integer> list = initList();
	private Spliterator<Integer> sit = list.spliterator();

	@Test
	public void split() {
		for (int i = 0; i < 4; i++) {
			new SplitThread().start();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("运行结果：" + count.intValue());
	}

	private List<Integer> initList() {
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i <= 10; i++) {
			result.add(i * 10);
		}
		return result;
	}

	class SplitThread extends Thread {
		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
			sit.trySplit().forEachRemaining(new Consumer<Integer>() {
				@Override
				public void accept(Integer t) {
					count.addAndGet(t);
					System.out.println("线程:" + threadName + " -- 数值：" + t);
				}
			});
			System.out.println("线程：" + threadName + " 运行结束");
		}
	}
}
