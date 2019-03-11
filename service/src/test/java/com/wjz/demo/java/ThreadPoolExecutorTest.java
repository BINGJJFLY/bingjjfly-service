package com.wjz.demo.java;

import org.junit.Test;

public class ThreadPoolExecutorTest {

	public void execute(Runnable task) {
		// do nothing
	}

	@Test
	public void constructor() {
		ThreadPoolExecutorTest executor = new ThreadPoolExecutorTest();
		executor.execute(null);
		
		ThreadPoolExecutorTest executor_2 = new ThreadPoolExecutorTest() {

		};
		executor_2.execute(null);
		
		ThreadPoolExecutorTest executor_3 = new ThreadPoolExecutorTest() {
			@Override
			public void execute(Runnable task) {
				if (task == null) {
					System.out.println("null.");
				}
				super.execute(task);
			}
		};
		executor_3.execute(null);
	}

}
