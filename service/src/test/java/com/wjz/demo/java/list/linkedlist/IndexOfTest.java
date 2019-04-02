package com.wjz.demo.java.list.linkedlist;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

/**
 * LinkedList查找元素下标
 * 
 * @author iss002
 *
 */
public class IndexOfTest {
	
	@Test
	public void contains() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// return indexOf(o) != -1;
		// 从前往后找，index初始化为0，循环累加
//		int index = 0;
//        if (o == null) {
//            for (Node<E> x = first; x != null; x = x.next) {
//                if (x.item == null)
//                    return index;
//                index++;
//            }
//        }
		Assert.assertEquals(true, ll.contains(6));
	}

	@Test
	public void indexOf() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// 参考contanins(element);
		Assert.assertEquals(2, ll.indexOf(6));
	}
	
	@Test
	public void lastIndexOf() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// 和indexOf(element)正好相反
		// 从后往前找，index初始化为size，循环累减
//		int index = size;
//        if (o == null) {
//            for (Node<E> x = last; x != null; x = x.prev) {
//                index--;
//                if (x.item == null)
//                    return index;
//            }
//        }
		Assert.assertEquals(2, ll.lastIndexOf(6));
	}
	
}
