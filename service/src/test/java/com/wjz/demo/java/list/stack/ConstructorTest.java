package com.wjz.demo.java.list.stack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

import org.junit.Test;

/**
 * 堆栈集合
 * 
 * Stack继承了Vector
 * 
 * ArrayList、LinkedList、Vector和Stack插入时都是在数组尾端插入，先进置于头端，后进置于尾端
 * ArrayList、LinkedList和Vector删除时都是从头端开始删除，先进先出 
 * Stack删除时从数组尾端开始删除，先进后出
 *
 * @author iss002
 *
 */
public class ConstructorTest {

	@Test
	public void array() {
		ArrayList<Integer> array = new ArrayList<>();
		array.add(1);
		array.add(2);
		array.add(3);

		// 先进先出
		Iterator<Integer> it = array.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
			it.remove();
		}
	}

	@Test
	public void linked() {
		LinkedList<Integer> linked = new LinkedList<>();
		linked.add(1);
		linked.add(2);
		linked.add(3);

		// 先进先出
		Iterator<Integer> it = linked.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
			it.remove();
		}
	}

	@Test
	public void vector() {
		Vector<Integer> vertor = new Vector<>();
		vertor.add(1);
		vertor.add(2);
		vertor.add(3);

		// 先进先出
		Iterator<Integer> it = vertor.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
			it.remove();
		}
	}

	@Test
	public void stack_1() {
		Stack<Integer> stack = new Stack<>();
		stack.add(1);
		stack.add(2);
		stack.add(3);

		// 先进先出
		Iterator<Integer> it = stack.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
			it.remove();
		}
	}

	@Test
	public void stack_2() {
		Stack<Integer> stack = new Stack<>();
		stack.add(1);
		stack.add(2);
		stack.add(3);

		// 先进后出
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}

}
