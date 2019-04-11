package com.wjz.demo.java.map.treemap;

import java.util.TreeMap;

import org.junit.Test;

/**
 * LinkedHashMap和TreeMap的区别：
 * 前者保证插入顺序但是不能自动根据key的值排序
 * 后者不保证插入顺序但是能自动根据key的值排序
 *
 * @author iss002
 *
 */
public class ConstructorTest {
	
	@Test
	public void general() {
		TreeMap<Integer, String> map = new TreeMap<>();
		map.put(3, "a");
		map.put(1, "b");
		map.put(5, "c");
		map.put(2, "d");
		map.put(7, "e");
		map.forEach((k, v) -> System.out.println(k + ":" + v));
	}

}
