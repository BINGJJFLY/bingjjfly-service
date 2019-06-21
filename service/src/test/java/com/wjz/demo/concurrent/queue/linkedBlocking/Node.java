package com.wjz.demo.concurrent.queue.linkedBlocking;

/**
 * 节点的成员变量不使用volatile修饰是因为操作之前都必须加锁
 *
 * @author iss002
 *
 * @param <E>
 */
public class Node<E> {
	
	E item;
	
	Node<E> next;

	Node(E x) {
		this.item = x;
	}
}
