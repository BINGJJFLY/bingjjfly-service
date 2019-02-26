package com.wjz.demo.java.list.arraylist;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

/**
 * ArrayList的元素下标
 * <p>
 * 如果list中的数据量很大这将是很慢的，因为涉及到循环元素查找下标
 * </p>
 * 
 * @author iss002
 *
 */
public class IndexOfTest {
	
	@Test
	public void contains() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(21);
		list.add(34);
		list.add(48);
		list.add(35);
		// return indexOf(o) >= 0;
		assertEquals(list.contains((Integer) 1), true);
	}
	
	@Test
	public void indexOfNotNull() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(21);
		list.add(34);
		list.add(48);
		list.add(35);
		// 非null元素，遍历数组：for (int i = 0; i < size; i++)，判断equals
		assertEquals(list.indexOf(21), 1);
	}
	
	@Test
	public void indexOfNull() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(21);
		list.add(null);
		list.add(48);
		list.add(null);
		// null元素，遍历数组：for (int i = 0; i < size; i++)，判断==
		assertEquals(list.indexOf(null), 2);
	}

	@Test
	public void lastIndexOf() {
		ArrayList<Integer> list = new ArrayList<>(5);
		list.add(5);
		list.add(1);
		list.add(5);
		list.add(8);
		list.add(5);
		
		assertEquals(list.lastIndexOf(5), 4);
	}
	
}
