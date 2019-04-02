package com.wjz.demo.java.list.linkedlist;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

/**
 * LinkedList查询元素
 * 
 * @author iss002
 *
 */
public class GetTest {
	
	@Test
	public void get() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// 校验index：index >= 0 && index < size;
		// return node(index).item;
		// add(index, element);和addAll(index, c);时有用到该方法，有讲解
		// 判断index在链条的前半部还是后半部然后遍历，找到指定下标指向的节点，返回节点对应的值
		Assert.assertEquals((Integer) 2, ll.get(1));
	}
	
	@Test
	public void getFirst() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// 返回链条的头端节点对应的值，如果链条头端节点为null抛出异常
		Assert.assertEquals((Integer) 1, ll.getFirst());
	}
	
	@Test
	public void getLast() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// 返回链条的尾端节点对应的值，如果链条尾端节点为null抛出异常
		Assert.assertEquals((Integer) 7, ll.getLast());
	}

	@Test
	public void element() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// 同getFirst();
		Assert.assertEquals((Integer) 1, ll.element());
	}
}
