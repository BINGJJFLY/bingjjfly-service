package com.wjz.demo.java.list.arraylist;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class ToArrayTest {
	
	@Test
	public void toArray() {
		int size = 5;
		ArrayList<Integer> list = new ArrayList<>(size);
		list.add(1);
		list.add(21);
		list.add(34);
		list.add(48);
		list.add(35);
		Integer[] is = new Integer[3];
		// 目标数组容量小于ArrayList元素个数则复制数组，新数组元素个数为size，类型为is的类型
		// Arrays.copyOf(elementData, size, a.getClass());
		Integer[] ia = list.toArray(is);
		assertEquals(ia.length, size);
		for (int i = 0; i < ia.length; i++) {
			System.out.println(ia[i]);
		}
	}
	
	@Test
	public void toArrayBigger() {
		int size = 5;
		ArrayList<Integer> list = new ArrayList<>(size);
		list.add(1);
		list.add(21);
		list.add(34);
		list.add(48);
		list.add(35);
		Integer[] is = new Integer[10];
		// 目标数组容量多于ArrayList元素个数，System.arraycopy(elementData, 0, a, 0, size);
		Integer[] ia = list.toArray(is);
		for (int i = 0; i < ia.length; i++) {
			System.out.println(ia[i]);
		}
	}
	
	@Test
	public void toArrayOver() {
		int size = 5;
		ArrayList<Integer> list = new ArrayList<>(size);
		list.add(1);
		list.add(21);
		list.add(34);
		list.add(48);
		list.add(35);
		Integer[] is = new Integer[3];
		is[0] = 7;
		is[1] = 8;
		is[2] = 9;
		Integer[] ia = list.toArray(is);
		for (int i = 0; i < ia.length; i++) {
			System.out.println(ia[i]);
		}
	}

}
