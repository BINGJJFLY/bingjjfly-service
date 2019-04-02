package com.wjz.demo.java.list.linkedlist;

/**
 * 双向链表结构
 * 
 * @author iss002
 *
 * @param <E>
 */
public class Node<E> {
	
	E item;
	Node<E> next;
	Node<E> prev;
	
	public Node(E item, Node<E> next, Node<E> prev) {
		this.item = item;
		this.next = next;
		this.prev = prev;
	}

}
