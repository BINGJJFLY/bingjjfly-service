package com.wjz.demo.concurrent.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Assert;
import org.junit.Test;

public class BooleanTest {
	
	@Test
	public void get() {
		AtomicBoolean b = new AtomicBoolean();
		Assert.assertEquals(false, b.get());
	}

	/*
	 public final boolean get() {
        return value != 0;
    }
	 */
	
	@Test
	public void compareAndSet() {
		AtomicBoolean b = new AtomicBoolean();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				// 该方法并非安全操作，布尔值可能被其他线程修改了
				System.out.println(b.compareAndSet(false, true));
			}
		});
		t.start();
		b.getAndSet(true);
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
