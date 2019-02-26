package com.wjz.demo.java.list.arraylist;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

/**
 * ArrayList初始化
 * 
 * @author iss002
 *
 */
public class ConstructorTest {
	
	@SuppressWarnings("unused")
	@Test
	public void capacity() {
		// 默认容量为10，空的数组：new Object[] = {};
		ArrayList<Integer> list = new ArrayList<>();
		// 定义容量为0，空的数组：new Object[] = {};
		list = new ArrayList<>(0);
		// 定义容量为10，数组为：new Object[10];
		list = new ArrayList<>(10);
		// 定义容量小于0，抛异常
		try {
			list = new ArrayList<>(-10);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Illegal Capacity: -10");
		}
	}
	
	@Test
	public void constructorWithCollection() {
		Collection<Integer> c = new ArrayList<>(7);
		// ArrayList.toArray()时复制了容量为size值的数组，此时size值为0
		// 所以此时的list还是一个默认容量为10，空的数组：new Object[] = {};
		ArrayList<Integer> list = new ArrayList<>(c);
		c.add(1);
		// 为size赋值，值为Collection内的数组长度即size值
		list = new ArrayList<>(c);
		assertEquals(list.size(), 1);
	}
	
}
