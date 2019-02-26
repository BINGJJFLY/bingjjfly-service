package com.wjz.demo.java.list.arraylist;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

/**
 * ArrayList克隆
 * 
 * @author iss002
 *
 */
public class CloneTest {
	
	@SuppressWarnings("unchecked")
	@Test
	public void cloneTest() {
		ArrayList<Integer> list = new ArrayList<>(5);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(6);
		list.add(7);
		
		// super.clone();
		// Arrays.copyOf(elementData, size);
		ArrayList<Integer> list2 = (ArrayList<Integer>) list.clone();
		Assert.assertEquals(list == list2, false);
		Assert.assertEquals(list, list2);
		Assert.assertEquals(5, list2.size());
	}

}
