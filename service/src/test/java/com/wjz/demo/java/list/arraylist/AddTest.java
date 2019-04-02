package com.wjz.demo.java.list.arraylist;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

/**
 * ArrayList增加元素
 * <p>有序</p>
 * <p>可重复</p>
 * <p>
 * 不指定元素新增只可能涉及到数组的扩容，速度不会很慢
 * </p>
 * <p>
 * 指定元素新增可能涉及到数组的扩容和数组的移动，如果数组容量很大元素很多且插入位置很靠前那么需要移动的元素数量就会很大，速度很慢
 * </p>
 * 
 * @author iss002
 *
 */
public class AddTest {
	
	@Test
	public void add() {
		int capacity = 5;
		// 是默认容量为5
		ArrayList<Integer> list = new ArrayList<>(capacity);
		// 填充元素时确保数组容量足够：ensureCapacityInternal
		// 充足时修改size值即加一并为数组为size值的下标指定元素赋值
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		// 此时数组容量不足，需要进行扩容，扩容后需要校验是否能够容纳新增后的元素集等
		// 数组复制重新赋值：elementData = Arrays.copyOf(elementData, newCapacity);
		// 为数组下标指向元素赋值同上
		howToGrow(capacity);
		list.add(6);
		
		System.out.println(Runtime.getRuntime().availableProcessors());
	}
	
	private void howToGrow(int oldCapacity) {
		// 近乎1/2倍扩容，加载因子为1即超过容量值时才会进行扩容，扩容因子为1.5
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		assertEquals(newCapacity, 7);
	}
	
	@Test
	public void addWithIndex() {
		// size为0
		ArrayList<Integer> list = new ArrayList<>(5);
		list.add(1);
		list.add(2);
		// 检查下标是否越界，未越界检查是否需要扩容（同上）
		// 复制数组：System.arraycopy(elementData, index, elementData, index + 1, size - index);
		// 类似(size是3，index是1，value是6)：1,2,null,null,null ==> 1,null,2,null,null ==> 1,6,2,null,null
		list.add(1, 6);
		for (Integer i : list) {
			System.out.println(i);
		}
	}
	
	@Test
	public void addAll() {
		ArrayList<Integer> list = new ArrayList<>(5);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		ArrayList<Integer> list2 = new ArrayList<>();
		// 获得Collection中的有效元素（size元素），判断是否扩容，数组复制，复制到size下标之后
		list2.addAll(list);
		assertEquals(list2.size(), 5);
	}
	
	@Test
	public void addAllWithIndex() {
		ArrayList<Integer> list = new ArrayList<>(5);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		ArrayList<Integer> list2 = new ArrayList<>();
		list2.add(11);
		list2.add(12);
		list2.add(13);
		// 判断下标是否越界， 同addAll()，判断下标是否为最后一个元素色下标值，不是的进行数组复制
		// 类似(size是3，index是1，value是1,2,3,4,5)：11,12,13,null,null,null,null,null,null,null ==> 11,12,13,null,null,null,12,13,null,null
		// 将Collection的数组复制到ArrayList数组中
		// 类似(size是3，index是1，value是1,2,3,4,5)：11,12,13,null,null,null,12,13,null,null ==> 11,1,2,3,4,5,12,13,null,null
		list2.addAll(1, list);
		assertEquals(list2.size(), 8);
	}

	@Test
	public void order() {
		ArrayList<String> list = new ArrayList<>(5);
		list.add("Hello");
		list.add("bingjjfly");
		list.add("1");
		list.add("Jxgyl");
		list.add("World");
		
		// 默认是在数组尾端添加元素，所以ArrayList是有序的
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void speedFast() {
		ArrayList<String> list = new ArrayList<>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			list.add(i + "");
		}
		long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end - start)); // 23：主要是循环耗时
	}
	
	@Test
	public void speedSlow() {
		ArrayList<String> list = new ArrayList<>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			list.add(0, i + "");
		}
		long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end - start)); // 1145：主要是添加耗时
	}
	
}
