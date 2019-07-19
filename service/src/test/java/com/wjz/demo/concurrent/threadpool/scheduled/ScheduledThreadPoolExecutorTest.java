package com.wjz.demo.concurrent.threadpool.scheduled;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

import org.junit.Test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class ScheduledThreadPoolExecutorTest {
	
	@Test
	public void corePoolSize() {
		// 继承ThreadPoolExecutor，是具备定时执行和周期执行功能的线程池
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
	}

	@Test
	public void full() {
		ThreadFactoryBuilder builder = new ThreadFactoryBuilder();
		ThreadFactory factory = builder.setNameFormat("Thread-Name-%s").build();
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5, factory, new AbortPolicy());
	}
	
	/*
	 public ScheduledThreadPoolExecutor(int corePoolSize) {
	 	$ 最大线程数为最大值，相当于无穷 $
        super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
              new DelayedWorkQueue());
     }
	 */
	
}
