package com.wjz.demo.concurrent.atomic;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;

import org.junit.Assert;
import org.junit.Test;

/**
 * LongAccumulator是功能更加强大的LongAdder，前者不仅可以提供累加还能提供其他运算，后者只能累加
 * 前者可提供初始值不为0的值，后者只能为0
 * 
 * @author admin
 *
 */
public class LongAccumulatorTest {
	
	@Test
	public void add() {
		LongAccumulator l = new LongAccumulator(new AddLongBinaryOperator(), 0);
		// 7 + 0
		l.accumulate(7);
		Assert.assertEquals(7, l.get());
	}
	
	@Test
	public void getThenRest() {
		LongAccumulator l = new LongAccumulator(new AddLongBinaryOperator(), 3);
		l.accumulate(7);
		Assert.assertEquals(10, l.getThenReset());
		Assert.assertEquals(3, l.get());
		l.accumulate(7);
		l.reset();
		Assert.assertEquals(3, l.get());
	}
	
	@Test
	public void multiply() {
		LongAccumulator m = new LongAccumulator(new MultiplyLongBinaryOperator(), 1);
		m.accumulate(2);
		Assert.assertEquals(2, m.longValue());
		m.accumulate(5);
		Assert.assertEquals(10, m.longValue());
	}

	class AddLongBinaryOperator implements LongBinaryOperator {

		@Override
		public long applyAsLong(long left, long right) {
			return left + right;
		}
		
	}
	
	class MultiplyLongBinaryOperator implements LongBinaryOperator {
		
		@Override
		public long applyAsLong(long left, long right) {
			return left * right;
		}
		
	}
}
