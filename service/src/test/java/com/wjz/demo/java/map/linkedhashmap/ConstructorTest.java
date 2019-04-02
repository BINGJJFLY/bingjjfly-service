package com.wjz.demo.java.map.linkedhashmap;

import java.util.LinkedHashMap;

import org.junit.Test;

/** 
 * LinkedHashMap的构造函数
 * 
 * LinkedHashMap继承了HashMap，前者内部为了一个双向链表以保证迭代顺序（插入顺序、最近最少访问顺序）
 * 增删查和HashMap相同，不需要顺序要求时使用HashMap即可
 * 
 * @author iss002
 *
 */
public class ConstructorTest {
	
	@Test
	public void general() {
		// 调用父类的构造函数
		// default initial capacity (16) and load factor (0.75).
		LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
	}

	@Test
	public void capacity() {
		// 调用父类的构造函数
		// the specified initial capacity and a default load factor (0.75).
		LinkedHashMap<String, Integer> map = new LinkedHashMap<>(10);
	}
	
	@Test
	public void capacityAndloadFactor() {
		// 调用父类的构造函数
		// the specified initial capacity and load factor.
		LinkedHashMap<String, Integer> map = new LinkedHashMap<>(10, 0.85f);
	}
	
	@Test
	public void capacityAndloadFactorAndAccessOrder() {
		// 其他构造函数默认的accessOrder为false，LinkedHashMap的迭代顺序：为true时表示最近最少访问顺序，为false时表示插入顺序
		// true for access-order, false for insertion-order.
		LinkedHashMap<String, Integer> map = new LinkedHashMap<>(10, 0.85f, true);
	}
	
	@Test
	public void map() {
		LinkedHashMap<String, Integer> map = new LinkedHashMap<>(10, 0.85f);
		map.put("id", 1);
		
		// 调用父类的无参构造函数
		// default initial capacity (16) and load factor (0.75).
		// 调用父类的putMapEntries(m, false);
		LinkedHashMap<String, Integer> map_2 = new LinkedHashMap<>(map);
	}
	
}
