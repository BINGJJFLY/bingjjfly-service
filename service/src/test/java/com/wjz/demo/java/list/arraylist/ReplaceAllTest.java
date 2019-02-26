package com.wjz.demo.java.list.arraylist;

import java.util.ArrayList;

import org.junit.Test;

/**
 * ArrayList全部替换
 * 
 * @author iss002
 *
 */
public class ReplaceAllTest {
	
	@Test
	public void num() {
		ArrayList<Integer> list = new ArrayList<>(5);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(6);
		list.add(7);
		
		list.replaceAll(val -> val + 1);
		list.forEach(val -> System.out.println(val));
	}

	@Test
	public void string() {
		ArrayList<String> list = new ArrayList<>(5);
		list.add("WORLD");
		list.add("BING");
		list.add("PING");
		list.add("JXGYL");
		list.add("BEIJING");
		
		list.replaceAll(val -> "HELLO " + val);
		list.forEach(val -> System.out.println(val));
	}
}
