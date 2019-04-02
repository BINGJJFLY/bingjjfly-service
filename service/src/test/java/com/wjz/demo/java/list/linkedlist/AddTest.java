package com.wjz.demo.java.list.linkedlist;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.Test;

/**
 * LinkedList增加
 * 
 * @author iss002
 *
 */
public class AddTest {
	
	@Test
	public void add() {
		LinkedList<Integer> ll = new LinkedList<>();
		// 默认放置在链尾：linkLast
		// 链条头端和尾端都不为null且都是7，节点前一个和后一个均为null
		ll.add(7);
		// 链条头端为7尾端为100，头节点前一个仍然为null后一个为100，尾节点前一个为7后一个节点为null
		ll.add(100);
		// 		 First						 							Last
		//   	   7						  100			              3
		// Prev(null) Next(100)			Prev(7) Next(3)			Prev(100) Next(null)
		ll.add(3);
	}
	
	@Test
	public void addByIndex() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(7);
		ll.add(100);
		ll.add(1);
		ll.add(6);
		ll.add(3);
		// index == size，同add();
		// 0 <= index < size，linkBefore();
		// Node node(index) {}：会判断下标的位置，如果位于链条的前半部则循环前半部（从前往后找），如果位于链条的后半部则循环后半部（从后往前找）
		// 前半部的返回后一个节点，后半部的返回前一个节点，返回的节点即为要下标指向的节点3(Index) --> 6(Node)
		// 创建新的节点：succ即返回的节点
		// final Node<E> pred = succ.prev;
        // final Node<E> newNode = new Node<>(pred, e, succ); 为新节点设置前一个节点为返回节点的前一个节点，设置后一个节点即返回节点本身
//		   succ.prev = newNode;
//	          if (pred == null)
//	            first = newNode;
//	          else
//	            pred.next = newNode;
//	       size++;
		// 为返回节点设置前一个节点为新创建的节点，如果插入在链条的头端则设置链条头端为新创建的节点，否则将返回节点的后一个节点设置为新节点
		// size值+1
		ll.add(3, 8);
	}
	
	@Test
	public void addFirst() {
		LinkedList<Integer> ll = new LinkedList<>();
		// 和add()、addLast()正好相反，在链条头端添加节点
		// null <-- 1 --<-->-- 77 --<-->-- 100 --> null
		ll.addFirst(100);
		ll.addFirst(77);
		ll.addFirst(1);
	}
	
	@Test
	public void addLast() {
		LinkedList<Integer> ll = new LinkedList<>();
		// 和add()相同
		// null <-- 1 --<-->-- 77 --<-->-- 100 --> null
		ll.addLast(1);
		ll.addLast(77);
		ll.addLast(100);
	}
	
	@Test
	public void addAll() {
		Collection<Integer> c = new LinkedList<>();
		c.add(2);
		c.add(6);
		
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(7);
		// 默认链尾添加，集合转数组
		// null <-- 1 --<-->-- 7 --<-->-- 2 --<-->-- 6 --> null
		// index入参为size
//		Node<E> pred, succ;
//		if (index == size) {
//            succ = null;
//            pred = last;
//        }
		// 遍历数组在链尾添加元素类似于add();
		// 设置链尾为集合的最后一个元素组成的节点
//		if (succ == null) {
//            last = pred;
//        }
		// size累加集合元素数
//		Object[] a = c.toArray();
//      int numNew = a.length;
//		size += numNew;
		ll.addAll(c);
	}
	
	@Test
	public void addAllByIndex() {
		Collection<Integer> c = new LinkedList<>();
		c.add(2);
		c.add(6);
		
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(7);
		ll.add(10);
		
		// null <-- 1 --<-->-- 7 --<-->-- 10 --> null
		// null <-- 1 --<-->-- 2 --<-->-- 6 --<-->-- 7 --<-->-- 10 --> null
		// 集合转数组
		// 找到参数下标指定的节点（ node(index) ），1为集合链条的头端，7为集合链条的尾端
//		Node<E> pred, succ;
//		else {
//            succ = node(index);
//            pred = succ.prev;
//        }
		// 遍历数组在参数下标指定节点的前一个节点添加元素类似于add();
		// size累加集合元素数
		ll.addAll(1, c);
	}
	
	@Test
	public void offer() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(7);
		ll.add(100);
		
		// 同add(e);默认在链尾添加节点
		ll.offer(3);
	}
	
	@Test
	public void offerFirst() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(7);
		ll.add(100);
		
		// 同addFirst(e);在链条头端添加节点
		ll.offerFirst(3);
	}
	
	@Test
	public void offerLast() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(7);
		ll.add(100);
		
		// 同addLast(e);在链条尾端添加节点
		ll.offerLast(3);
	}
	
	@Test
	public void push() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(7);
		ll.add(100);
		
		// 与add()正好相反，add()是将元素添加到链条尾端，push()是将元素添加到链条头端，同addLast();
		ll.push(3);
	}
}
