package com.wjz.demo.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

import org.junit.Assert;
import org.junit.Test;

public class IntegerArrayTest {
	
	@Test
	public void get() {
		int[] vals = new int[] {1, 3};
		AtomicIntegerArray aia = new AtomicIntegerArray(vals);
		// 将修改写回主内存
		Assert.assertEquals(1, aia.getAndSet(0, 7));
		// unsafe.getIntVolatile读取主内存信息
		Assert.assertEquals(7, aia.get(0));
		Assert.assertEquals(1, vals[0]);
	}

	/*
	 public AtomicIntegerArray(int[] array) {
        // Visibility guaranteed by final field guarantees
        this.array = array.clone();
    }
	 */
}
