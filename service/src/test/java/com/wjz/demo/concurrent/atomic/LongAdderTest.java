package com.wjz.demo.concurrent.atomic;

import java.util.concurrent.atomic.LongAdder;

import org.junit.Assert;
import org.junit.Test;

public class LongAdderTest {
	
	@Test
	public void add() {
		LongAdder l = new LongAdder();
		l.add(7);
		Assert.assertEquals(7, l.sum());
		Assert.assertEquals(7, l.longValue());
		Assert.assertEquals(7, l.intValue());
		Assert.assertEquals(7, l.shortValue());
		
		Assert.assertEquals(7, l.sumThenReset());
		Assert.assertEquals(0, l.sum());
		l.increment();
		Assert.assertEquals(1, l.sum());
		l.decrement();
		Assert.assertEquals(0, l.sum());
		
		double d = Double.longBitsToDouble(l.sum()) + 7.67D;
		Assert.assertEquals(7.67+"", d+"");
	}

}
