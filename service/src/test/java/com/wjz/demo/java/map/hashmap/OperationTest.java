package com.wjz.demo.java.map.hashmap;

import org.junit.Assert;
import org.junit.Test;

public class OperationTest {

	@Test
	public void or() {
		int a = 60; /**0011 1100**/
		int b = 13; /**0000 1101**/
		int c = 0;
		c = a | b; /**0011 1101**/
		Assert.assertEquals(61, c);
	}
	
	@Test
	public void orAndEquals() {
		int a = 60; /**0011 1100**/
		int b = 13; /**0000 1101**/
		int c = 0;
		// a = a | b; --> c = a;
		c = a |= b; /**0011 1101**/
		Assert.assertEquals(61, a);
		Assert.assertEquals(61, c);
	}
	
	@Test
	public void youyi() {
		// 001 001 --> 001 --> 1
		// 010 001 --> 011 --> 3
		// 011 001 --> 011 --> 3
		// 100 010 --> 110 --> 6
		// 101 010 --> 111 --> 7
		 int n = 5;
	     n |= n >>> 1;
	     System.out.println(n);
	}
}
