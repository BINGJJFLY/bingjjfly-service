package com.wjz.demo.java.list.linkedlist;

import java.util.LinkedList;

import org.junit.Test;

/**
 * LinkedList清空
 * 
 * @author iss002
 *
 */
public class ClearTest {
	
	@Test
	public void clear() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
//		for (Node<E> x = first; x != null; ) {
//            Node<E> next = x.next;
//            x.item = null;
//            x.next = null;
//            x.prev = null;
//            x = next;
//        }
//        first = last = null;
//        size = 0;
		// 这种写发同
//		for (Node<E> x = first; x != null; x = x.next) {
//            x.item = null;
//            x.next = null;
//            x.prev = null;
//        }
//        first = last = null;
//        size = 0;
		// 从前往后遍历整条链条，将各个节点的值、节点对应的前一个节点引用、节点对应的后一个节点引用均置为null
		// 链条头端和尾端均置为null
		// size置为0
		ll.clear();
	}

}
