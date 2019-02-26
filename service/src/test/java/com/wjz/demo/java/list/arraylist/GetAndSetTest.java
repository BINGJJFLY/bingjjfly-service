package com.wjz.demo.java.list.arraylist;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

/**
 * ArrayList查询和设置元素
 * <p>
 * 这将是很快的操作，所以是快速随机访问
 * </p>
 * 
 * @author iss002
 *
 */
public class GetAndSetTest {

	@Test
	public void get() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(11);
		list.add(21);
		list.add(31);
		list.add(16);
		list.add(7);
		
		// 检查下标是否越界，数组下标指定元素直接返回：elementData[index]；
		assertEquals(list.get(1), (Integer) 21);
	}
	
	@Test
	public void set() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(11);
		list.add(21);
		list.add(31);
		list.add(16);
		list.add(7);
		
		// 检查下标是否越界，数组下标指定元素直接赋值：elementData[index] = newElement；
		list.set(1, (Integer) 100);
		assertEquals(list.get(1), (Integer) 100);
	}
	
}
