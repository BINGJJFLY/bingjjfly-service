package com.wjz.demo.concurrent;

import org.junit.Test;

import com.wjz.demo.concurrent.task.PrintTask;
import com.wjz.demo.concurrent.task.SleepTask;
import com.wjz.demo.concurrent.thread.DefaultThreadPoolTaskExecutor;

public class ThreadPoolTaskExecutorTest {

	@Test
	public void execute() {
		DefaultThreadPoolTaskExecutor tpte = new DefaultThreadPoolTaskExecutor();
		for (int i = 0; i < 20; i++) {
			tpte.execute(new PrintTask());
			tpte.execute(new SleepTask());
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
