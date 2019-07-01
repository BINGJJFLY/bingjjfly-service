package com.wjz.demo.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;

public class IntegerTest {
	
	@Test
	public void add() {
		AtomicInteger i = new AtomicInteger();
		Assert.assertEquals(0, i.getAndIncrement());
		Assert.assertEquals(1, i.get());
	}

	/*
	 private volatile int value;
	 */
	
	/*
	 public final int getAndIncrement() {
        return unsafe.getAndAddInt(this, valueOffset, 1);
    }
	 */
	
	/*
	 public final int getAndAddInt(Object o, long offset, int delta) {
        int v;
        do {
            v = getIntVolatile(o, offset);
        } while (!compareAndSwapInt(o, offset, v, v + delta));
        return v;
    }   
	 */
}
