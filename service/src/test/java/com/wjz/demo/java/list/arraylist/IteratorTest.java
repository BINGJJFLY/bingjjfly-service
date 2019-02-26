package com.wjz.demo.java.list.arraylist;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

/**
 * ArrayList迭代器
 * <p>
 * 只能正向查找
 * </p>
 * 
 * @author iss002
 *
 */
public class IteratorTest {
	
	@Test
	public void iterator() {
		ArrayList<Integer> list = new ArrayList<>(5);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		// 内部类ArrayList.Itr
		Iterator<Integer> it = list.iterator();
		// 指针不是指向最后一个元素返回true：return cursor != size;
		Assert.assertEquals(true, it.hasNext());
		
		// 内部类默认拥有外部类的引用，所以可以拿到ArrayList的elementData、size等属性
		// int i = cursor;
		// cursor = i + 1;
        // return (E) elementData[lastRet = i];
		// 正向查找
		it.next();
		
		// 必须得先next才能删除，lastRet为0
		// ArrayList.this.remove(lastRet);
        // cursor = lastRet;
        // lastRet = -1;
		it.remove();
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
	}

	@Test
	public void forEach() {
		ArrayList<Integer> list = new ArrayList<>(5);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		Iterator<Integer> it = list.iterator();
		it.forEachRemaining(val->System.out.println(val));
	}
}
