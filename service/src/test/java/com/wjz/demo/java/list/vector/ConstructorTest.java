package com.wjz.demo.java.list.vector;

import java.util.Vector;

import org.junit.Test;

/**
 * 矢量（既有大小又有方向）集合
 * 
 * 相比于ArrayList，该集合所有的核心方法都加了锁线程安全
 * 扩容方式：没有设置扩容因子时两倍扩容，设置了扩容因子时按扩容因子值大小扩容
 * 线程安全的List集合的其他方式：Collections.synchronizedList(new ArrayList<>());
 *
 * @author iss002
 *
 */
public class ConstructorTest {
	
	/*
	 protected Object[] elementData;
	 protected int elementCount;
	 protected int capacityIncrement;
	 
	 三个成员变量：数组、元素个数、容量增长因子
	 */
	
	@Test
	public void general() {
		// 默认创建容量为10的数组，容量增长因子为0
		Vector<Integer> vertor = new Vector<>();
	}
	
	@Test
	public void capacity() {
		// 创建指定容量的数组，容量增长因子为0
		Vector<Integer> vertor = new Vector<>(16);
	}

	@Test
	public void capacityAndIncrement() {
		// 创建指定容量的数组，指定容量增长因子
		Vector<Integer> vertor = new Vector<>(10, 10);
	}
	
	@Test
	public void collection() {
		Vector<Integer> vertor = new Vector<>(10, 10);
		Vector<Integer> vertor_2 = new Vector<>(vertor);
	}
	
	/*
	 public Vector(Collection<? extends E> c) {
	 	$** 入参集合转为数组并为elementData赋值 **$
        elementData = c.toArray();
        $** elementCount为数组长度 **$
        elementCount = elementData.length;
        // c.toArray might (incorrectly) not return Object[] (see 6260652)
        $** 无论入参集合转的数组是什么类型，数组复制的结果数组的类型保证是Object[].class **$
        if (elementData.getClass() != Object[].class)
            elementData = Arrays.copyOf(elementData, elementCount, Object[].class);
     }
	 */
}
