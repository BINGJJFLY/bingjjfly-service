package com.wjz.demo.java.map.hashmap;

import java.util.HashMap;
import java.util.function.BiFunction;

import org.junit.Assert;
import org.junit.Test;

public class ComputeTest {
	
	@Test
	public void compute_1() {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("id", 1);
		
		// v这个参数相当于是没有用到，接口中传参时"id"对应的值"1"，方法函数接参为key，value
//		V oldValue = (old == null) ? null : old.value;
//        V v = remappingFunction.apply(key, oldValue);
		Integer i = map.compute("id", (k, v) -> (v != null) ? v + 1 : null);
		Integer j = map.get("id");
		Assert.assertEquals((Integer) 2, i);
		Assert.assertEquals((Integer) 2, j);
	}

	@Test
	public void compute_2() {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("id", 1);
		
		Integer i = map.compute("id", (k, v) -> 2);
		Integer j = map.get("id");
		Assert.assertEquals((Integer) 2, i);
		Assert.assertEquals((Integer) 2, j);
	}
	
	@Test
	public void compute_3() {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("id", 1);
		
		Integer i = map.compute("id", new BiFunction<String, Integer, Integer>() {
			@Override
			public Integer apply(String k, Integer v) {
				return replace(v);
			}
		});
		Integer j = map.get("id");
		Assert.assertEquals((Integer) 2, i);
		Assert.assertEquals((Integer) 2, j);
	}
	
	private int replace(int v) {
		return v + 1;
	}
	
}
