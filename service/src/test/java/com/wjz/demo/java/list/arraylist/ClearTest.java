package com.wjz.demo.java.list.arraylist;

import java.util.ArrayList;

import org.junit.Test;

/**
 * ArrayList清空
 * 
 * @author iss002
 *
 */
public class ClearTest {
	
	@Test
	public void clear() {
		ArrayList<Integer> list = new ArrayList<>(5);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
//		for (int i = 0; i < size; i++)
//            elementData[i] = null;
//
//        size = 0;
		// 遍历所有元素均置为null，size置为0
		list.clear();
	}

}
