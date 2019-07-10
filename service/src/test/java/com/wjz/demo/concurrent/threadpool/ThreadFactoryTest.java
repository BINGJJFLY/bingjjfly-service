package com.wjz.demo.concurrent.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.junit.Test;

public class ThreadFactoryTest {
	
	@Test
	public void init() {
		ThreadFactory factory = Executors.defaultThreadFactory();
//		Thread t = new Thread(group, r, namePrefix+threadNumber.getAndIncrement(), 0);
		Thread t = factory.newThread(new Task());
		System.out.println(t.getName());
		System.out.println(t.getPriority());
		System.out.println(t.getState().name());
		System.out.println(t.isDaemon());
		System.out.println(t.isAlive());
	}

	class Task implements Runnable {

		@Override
		public void run() {
			
		}
		
	}
}
