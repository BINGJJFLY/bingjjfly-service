package com.wjz.demo.java.list.linkedlist;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

/**
 * LinkedList返回链端对应的值
 * 
 * @author iss002
 *
 */
public class PeekTest {
	
	@Test
	public void peek() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// 返回链条头端对应的值
//		final Node<E> f = first;
//        return (f == null) ? null : f.item;
		Assert.assertEquals((Integer) 1, ll.peek());
	}
	
	@Test
	public void peekFirst() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// 同peek();
		Assert.assertEquals((Integer) 1, ll.peekFirst());
	}
	
	@Test
	public void peekLast() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// 返回链条尾端对应的值
//		final Node<E> l = last;
//        return (l == null) ? null : l.item;
		Assert.assertEquals((Integer) 7, ll.peekLast());
	}

}
