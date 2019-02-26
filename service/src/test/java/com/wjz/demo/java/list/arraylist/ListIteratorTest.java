package com.wjz.demo.java.list.arraylist;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.ListIterator;

import org.junit.Test;

/**
 * ArrayList集合迭代器
 * <p>
 * 既能正向查找还支持反向查找，但是得先正向才能反向
 * </p>
 * <p>
 * 支持set值和add值
 * </p>
 * 
 * @author iss002
 *
 */
public class ListIteratorTest {
	
	@Test
	public void listIterator() {
		ArrayList<Integer> list = new ArrayList<>(5);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(6);
		list.add(7);
		
		ListIterator<Integer> lit = list.listIterator();
		// return cursor != 0;
		assertEquals(false, lit.hasPrevious());
		// see Itr.next();
		assertEquals((Integer) 1, lit.next());
		assertEquals(true, lit.hasPrevious());
		// return cursor;
		assertEquals(1, lit.nextIndex());
		// return cursor - 1;
		assertEquals(0, lit.previousIndex());
		// previous()方法和next()方法操作正好相反
		assertEquals((Integer) 1, lit.previous());
	}
	
	@Test
	public void aesAndDesc() {
		ArrayList<Integer> list = new ArrayList<>(5);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(6);
		list.add(7);
		
		ListIterator<Integer> lit = list.listIterator();
		while (lit.hasNext()) {
			lit.next();
		}
		while (lit.hasPrevious()) {
			System.out.println(lit.previous());
		}
	}

	@Test
	public void set() {
		ArrayList<Integer> list = new ArrayList<>(5);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(6);
		list.add(7);
		
		ListIterator<Integer> lit = list.listIterator();
		lit.next();
		// ArrayList.this.set(lastRet, e);
		lit.set(100);

		while (lit.hasPrevious()) {
			lit.previous();
		}
		while (lit.hasNext()) {
			System.out.println(lit.next());
		}
	}
	
	@Test
	public void add() {
		ArrayList<Integer> list = new ArrayList<>(5);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(6);
		list.add(7);
		
		ListIterator<Integer> lit = list.listIterator();
		lit.next();
//		int i = cursor;
//      ArrayList.this.add(i, e);
//      cursor = i + 1;
//      lastRet = -1;
		// 可能涉及到数组大范围的移动
		lit.add(77);
		
		while (lit.hasPrevious()) {
			lit.previous();
		}
		while (lit.hasNext()) {
			System.out.println(lit.next());
		}
	}
}
