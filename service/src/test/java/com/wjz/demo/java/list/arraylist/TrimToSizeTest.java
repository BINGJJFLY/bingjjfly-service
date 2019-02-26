package com.wjz.demo.java.list.arraylist;

import java.util.ArrayList;

import org.junit.Test;

/**
 * ArrayList的缩容方式
 * <p>
 * 删除元素时会将元素置为null让GC工作回收对象但是不会主动缩容
 * </p>
 * <p>
 * 缩短容量至集合正在容纳的元素数量
 * </p>
 * 
 * @author iss002
 *
 */
public class TrimToSizeTest {

	@Test
	public void trimToSize() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(6);
		list.add(7);
		
		// 1,2,3,6,7,null,null,null,null,null ==> 1,2,3,6,7
		// 没有元素的直接置为空数组：{};
		// Arrays.copyOf(elementData, size);
		list.trimToSize();
	}
}
