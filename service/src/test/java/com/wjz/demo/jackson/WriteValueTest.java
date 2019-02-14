package com.wjz.demo.jackson;

import org.junit.Test;

import com.wjz.service.utils.JacksonUtils;

public class WriteValueTest {

	@Test
	public void array() {
		String[] ss = new String[] { "hello", "world" };
		try {
			System.out.println(ss.getClass().getName());
			System.out.println(ss.getClass().isArray());
			System.out.println(JacksonUtils.writeValueAsString(ss));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
