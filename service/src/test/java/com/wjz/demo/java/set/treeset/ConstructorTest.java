package com.wjz.demo.java.set.treeset;

import java.util.TreeSet;

import org.junit.Test;

/**
 * LinkedHashSet和TreeSet的区别： 
 * 前者保证插入顺序但是不能自动根据元素的值排序 
 * 后者不保证插入顺序但是能自动根据元素的值排序
 * 
 *
 * @author iss002
 *
 */
public class ConstructorTest {

	@Test
	public void general() {
		TreeSet<Integer> set = new TreeSet<>();
		set.add(3);
		set.add(1);
		set.add(5);
		set.add(2);
		set.add(7);
		set.forEach((e) -> System.out.println(e));
	}

}
