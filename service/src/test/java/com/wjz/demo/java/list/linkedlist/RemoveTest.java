package com.wjz.demo.java.list.linkedlist;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

/**
 * LinkedList删除元素
 * 
 * @author iss002
 *
 */
public class RemoveTest {

	@Test
	public void remove() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// 默认删除链条头端，return removeFirst();
		// 先进先出
		// 校验链条头端是否为null，是的话抛出异常，不是的话，将头端节点对应的值和对应的下一个节点的引用置为null（让GC工作）
//		final E element = f.item;
//        final Node<E> next = f.next;
//        f.item = null;
//        f.next = null; // help GC
		// 下一个节点设置为链条的新头端：first = next;
		// 下一节点对应的前一个节点的引用置为null：next.prev = null;
//		if (next == null)
//        last = null;
		// 如果新头端为null：将链条尾端置为null
		// size--
		// 返回被删除的元素
		Assert.assertEquals((Integer) 1, ll.remove());
	}
	
	@Test
	public void removeFirst() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// 同remove();
		Assert.assertEquals((Integer) 1, ll.removeFirst());
	}
	
	@Test
	public void removeLast() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// 同remove()正好相反;
		Assert.assertEquals((Integer) 1, ll.removeLast());
	}

	@Test
	public void removeByElement() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// 区分element是否为null，null判断相同：x.item == null，非null判断相同：o.equals(x.item)
		// 从链条头端遍历到链条尾端判断是否有相同的元素：for (Node<E> x = first; x != null; x = x.next)
		// 删除节点：unlink(x); x为目标删除节点
		ll.remove((Integer) 6);
	}
	
	@Test
	public void removeByIndex() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// return unlink(node(index))
		Assert.assertEquals((Integer) 2, ll.remove(1));
	}
	
	@Test
	public void removeFirstOccurrence() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// 同remove(element);从前往后遍历找到删除
		ll.removeFirstOccurrence(1);
	}
	
	@Test
	public void removeLastOccurrence() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// 和remove(element);正好相反，从后往前遍历找到删除：for (Node<E> x = last; x != null; x = x.prev)
		ll.removeLastOccurrence(7);
	}
	
	@Test
	public void poll() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// 类似于remove()和removeFirst()
		// 但是当链条头端为null时不会抛出异常，只是会返回null
		ll.poll();
	}
	
	@Test
	public void pollFirst() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// 同poll();
		ll.pollFirst();
	}
	
	@Test
	public void pollLast() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// 类似于removeLast();
		// 但是当链条尾端为null时不会抛出异常，只是会返回null
		ll.pollLast();
	}
	
	@Test
	public void pop() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(2);
		ll.add(6);
		ll.add(7);
		
		// 与removeFirst()相同
		Assert.assertEquals((Integer) 1, ll.pop());
	}
}
