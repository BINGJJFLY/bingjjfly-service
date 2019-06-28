package com.wjz.demo.concurrent.deque;

public class Node<E> {
	
	E item;
	
	Node<E> prev;
	
	Node<E> next;
	
	Node(E x) {
		item = x;
	}

}
