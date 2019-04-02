package com.wjz.demo.java.set.hashset;

import java.util.HashSet;

import org.junit.Test;

/**
 * HashSet的新增
 * 
 * @author iss002
 *
 */
public class AddTest {
	
	@Test
	public void add() {
		HashSet<String> set = new HashSet<>();
		// 利用Map的key来进行hash存储唯一元素
		set.add("id");
	}

	/*
	 public boolean add(E e) {
        return map.put(e, PRESENT)==null;
     }
	 */
}
