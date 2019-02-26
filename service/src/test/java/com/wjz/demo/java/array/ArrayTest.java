package com.wjz.demo.java.array;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

/**
 * 数组扩容
 * 
 * @author iss002
 *
 */
public class ArrayTest {

	@Test
	public void copyofEmpty() {
		Object[] array = new Object[10];
		assertEquals(array.length, 10);
		Object[] newArray = Arrays.copyOf(array, 0);
		assertEquals(newArray.length, 0);
		Object[] newerArray = Arrays.copyOf(array, 7);
		assertEquals(newerArray.length, 7);
	}
	
	@Test
	public void copyof() {
		Object[] array = new Object[10];
		array[0] = 1;
		array[1] = 7;
		Object[] newArray = Arrays.copyOf(array, 0);
		assertEquals(newArray.length, 0);
		Object[] newerArray = Arrays.copyOf(array, array.length);
		assertEquals(newerArray.length, 10);
		assertEquals(newerArray[0], 1);
		assertEquals(newerArray[1], 7);
	}
	
	@Test
	public void copyofNewType() {
		String[] array = new String[10];
		array[0] = "hello";
		array[1] = "world";
		String[] newArray = Arrays.copyOf(array, array.length, String[].class);
		
		assertEquals(newArray.getClass(), String[].class);
		assertEquals(newArray[0], "hello");
		assertEquals(newArray[1], "world");
		
		Object[] newerArray = Arrays.copyOf(array, array.length, Object[].class);
		assertEquals(newerArray.getClass(), Object[].class);
		assertEquals(newerArray[0], "hello");
		assertEquals(newerArray[1], "world");
		try {
			Arrays.copyOf(array, array.length, Integer[].class);
		} catch (Exception e) {
			assertEquals(e instanceof ArrayStoreException, true);
		}
	}
}
