package com.wjz.demo.java;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.junit.Test;

public class BigDecimalTest {
	
	@Test
	public void format() {
		BigDecimal bd = new BigDecimal(7843.837);
		System.out.println(new DecimalFormat("#.##").format(bd));
		
		BigDecimal bd2 = new BigDecimal(1121.83714);
		System.out.println(new DecimalFormat("#.####").format(bd2));
	}

	@Test
	public void string() {
		Long l = 8L;
		BigDecimal bd = new BigDecimal(l);
		System.out.println(String.valueOf(bd.longValue()));
		
		Byte b = 127;
		System.out.println(String.valueOf(b));
		System.out.println(new DecimalFormat("#.##").format(b));
		
		Short s = 9999;
		System.out.println(String.valueOf(s));
		System.out.println(new DecimalFormat("#.##").format(s));
	}
}
