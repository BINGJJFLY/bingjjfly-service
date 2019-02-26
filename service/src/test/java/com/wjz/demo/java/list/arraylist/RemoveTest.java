package com.wjz.demo.java.list.arraylist;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

/**
 * ArrayList删除元素
 * <p>
 * 删除时指定下标时最多涉及到数组移动但是速度不会很慢
 * 如果下标为最大值那直接将下标指引数组元素设置为null而不用移动数组速度很快，如果下标值较小那需要移动的元素数量就会很大速度较慢
 * </p>
 * <p>
 * 删除时如果不是指定下标而是删除元素则需要遍历数组速度可能很慢
 * 如果指定元素位于数组靠前位置甚至是第一个那遍历次数不很很大速度相对的不会太慢
 * 如果指定元素位于数组靠后位置甚至是最后一个那将是漫长的删除
 * </p>
 * 
 * @author iss002
 *
 */
public class RemoveTest {

	@Test
	public void removeByObject() {
		ArrayList<Integer> list = new ArrayList<>(5);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(6);
		list.add(7);
		
		// 1,2,3,6,7 ==> 1,2,6,7,7 ==> 1,2,6,7,null
		// 判断是否为null，找到第一个相等的元素的下标
		list.remove((Integer) 3);
		Assert.assertEquals(list.size(), 4);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void removeByIndex() {
		ArrayList<Integer> list = new ArrayList<>(5);
		list.add(11);
		list.add(21);
		list.add(31);
		list.add(16);
		list.add(7);
		
		// 下标越界判断，找到下标对应的值最终返回，删除手法同上，可以调用fastRemove方法
		Integer oldValue = list.remove(2);
		assertEquals(oldValue, (Integer) 31);
	}
	
	@Test
	public void removeAll() {
		ArrayList<Integer> list = new ArrayList<>(5);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(6);
		list.add(7);
		
		ArrayList<Integer> list2 = new ArrayList<>(2);
		list2.add(2);
		list2.add(6);
		
		// 最终效果图：1,2,3,6,7 ==> 1,3,7,null,null
		// 1,2,3,6,7 ==> 1,3,7,6,7 被删除的元素被移动到了数组尾端
		// 数组尾端的元素全部置为null，size值改变
		// for (int i = w; i < size; i++)
        //    elementData[i] = null;
		list.removeAll(list2);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void removeAllTest() {
		int size = 5;
		ArrayList<Integer> c = new ArrayList<>(2);
		c.add(2);
		c.add(6);
		
		final Object[] elementData = new Object[] {1,2,3,6,7};
        int r = 0, w = 0;
        try {
            for (; r < size; r++)
                if (c.contains(elementData[r]) == false)
                    elementData[w++] = elementData[r];
        } finally {
			
		}
	}
	
	@Test
	public void retainAll() {
		ArrayList<Integer> list = new ArrayList<>(5);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(6);
		list.add(7);
		
		ArrayList<Integer> list2 = new ArrayList<>(2);
		list2.add(2);
		list2.add(6);
		
		// 最终效果图：1,2,3,6,7 ==> 2,6,null,null,null
		// 1,2,3,6,7 ==> 2,6,3,6,7 被保留的元素被移动到了数组最前边
		// 数组尾端的元素全部置为null，size值改变
		// for (int i = w; i < size; i++)
        //    elementData[i] = null;
		list.retainAll(list2);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void retainAllTest() {
		int size = 5;
		ArrayList<Integer> c = new ArrayList<>(2);
		c.add(2);
		c.add(6);
		
		final Object[] elementData = new Object[] {1,2,3,6,7};
        int r = 0, w = 0;
        try {
            for (; r < size; r++)
                if (c.contains(elementData[r]) == true)
                    elementData[w++] = elementData[r];
        } finally {
			
		}
	}
	
	@Test
	public void speedFast() {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < 100000; i++) {
			list.add(i + "");
		}
		long start = System.currentTimeMillis();
		for (int i = 0; i < list.size(); ) {
			list.remove(list.size() - 1);
		}
		long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end - start)); // 2：主要是循环耗时
	}
	
	@Test
	public void speedSlow() {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < 100000; i++) {
			list.add(i + "");
		}
		long start = System.currentTimeMillis();
		for (int i = 0; i < list.size(); ) {
			list.remove(0);
		}
		long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end - start)); // 1018：主要是极广范围的数组移动耗时
	}
	
	@Test
	public void speedSlower() {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < 100000; i++) {
			list.add("0");
		}
		long start = System.currentTimeMillis();
		for (int i = 0; i < list.size(); ) {
			list.remove("0");
		}
		long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end - start)); // 1108：既有一次循环还有极广范围的数组移动
	}
	
	@Test
	public void speedSlowest() {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < 100000; i++) {
			list.add(i + "");
		}
		long start = System.currentTimeMillis();
		for (int i = 0; i < list.size(); ) {
			list.remove(list.size()-1+"");
		}
		long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end - start)); // 不会有数组移动但是有很漫长时间的循环
	}
	
}
