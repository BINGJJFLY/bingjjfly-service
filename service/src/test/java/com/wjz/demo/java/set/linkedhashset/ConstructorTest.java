package com.wjz.demo.java.set.linkedhashset;

import java.util.LinkedHashSet;

import org.junit.Test;

/**
 * LinkedHashSet的构造函数
 * 
 * LinkedHashSet继承了HashSet，内部维护了一个LinkedHashMap保证元素的迭代顺序
 * 
 * @author iss002
 *
 */
public class ConstructorTest {
	
	@Test
	public void general() {
		// empty linked hash set with the default initial capacity (16) and load factor (0.75).
		LinkedHashSet<String> set = new LinkedHashSet<>();
	}

	@Test
	public void capacity() {
		// empty linked hash set with the specified initial capacity and the default load factor (0.75).
		LinkedHashSet<String> set = new LinkedHashSet<>(10);
	}
	
	@Test
	public void capacityAndloadFactor() {
		// empty linked hash set with the specified initial capacity and load factor.
		LinkedHashSet<String> set = new LinkedHashSet<>(10, 0.85f);
	}
	
	@Test
	public void collection() {
		LinkedHashSet<String> set = new LinkedHashSet<>(10, 0.85f);
		set.add("id");
		
//		super(Math.max(2*c.size(), 11), .75f, true);
//      addAll(c);
		LinkedHashSet<String> set_2 = new LinkedHashSet<>(set);
	}
}
