package com.wjz.demo.java.list.linkedlist;

import java.util.LinkedList;

import org.junit.Test;

/**
 * LinkedList克隆
 * 
 * @author iss002
 *
 */
public class CloneTest {
	
	@Test
	public void cloneTest() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// Put clone into "virgin" state
//		for (Node<E> x = first; x != null; x = x.next)
//            clone.add(x.item);
		ll.clone();
	}

}
