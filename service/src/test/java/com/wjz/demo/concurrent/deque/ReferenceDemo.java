package com.wjz.demo.concurrent.deque;

public class ReferenceDemo {
	
	public static void main(String[] args) {
		Node<String> last = new Node<String>("last");
		Node<String> l = last; // 可以看成Node<String> l = new Node<String>("last");
		Node<String> node = new Node<String>("node");
		last = node; // 可以看成Node<String> last = new Node<String>("node");
		System.out.println(l);
	}

}
