package com.wjz.demo.java.list.linkedlist;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

public class ToArrayTest {

	@Test
	public void toArray() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		// 创建一个Object[]数组，遍历链条节点为数组赋值
//		Object[] result = new Object[size];
//        int i = 0;
//        for (Node<E> x = first; x != null; x = x.next)
//            result[i++] = x.item;
//        return result;
		Object[] array = ll.toArray();
		Assert.assertEquals(ll.size(), array.length);
	}
	
	@Test
	public void toArray_2() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		
		// 数组的长度小于链条的长度时初始化一个链条长度的数组
//		if (a.length < size)
//            a = (T[])java.lang.reflect.Array.newInstance(
//                                a.getClass().getComponentType(), size);
		// 遍历链条为数组赋值
//		int i = 0;
//        Object[] result = a;
//        for (Node<E> x = first; x != null; x = x.next)
//            result[i++] = x.item;
		Integer[] ii = ll.toArray(new Integer[0]);
		for (int i = 0; i < ii.length; i++) {
			System.out.println(ii[i]);
		}
		System.out.println();
		// 数组长度大于链条的长度时，数组size下标指向的元素置为null
		Integer[] is = ll.toArray(new Integer[] {100, 101, 102, 103});
		for (int i = 0; i < is.length; i++) {
			System.out.println(is[i]);
		}
	}
	
	
}
