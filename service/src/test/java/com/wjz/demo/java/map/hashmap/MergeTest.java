package com.wjz.demo.java.map.hashmap;

import java.util.HashMap;
import java.util.function.BiFunction;

import org.junit.Assert;
import org.junit.Test;

public class MergeTest {
	
	@Test
	public void merge_1() {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("id", 1);
		// kv=1;v=9;apply()=kv+v=1+9;
		Integer i = map.merge("id", 9, (kv, v) -> kv + v);
		Assert.assertEquals((Integer) 10, i);
	}

	@Test
	public void merge_2() {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("id", 1);
		// 替换
		Integer i = map.merge("id", 9, new BiFunction<Integer, Integer, Integer>() {
			@Override
			public Integer apply(Integer kv, Integer v) {
				return replace(kv, v);
			}
		});
		Assert.assertEquals((Integer) 10, i);
	}
	
	@Test
	public void merge_3() {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("id", 1);
		// 填充
		Integer i = map.merge("age", 2, new BiFunction<Integer, Integer, Integer>() {
			@Override
			public Integer apply(Integer kv, Integer v) {
				return replace(kv, v);
			}
		});
		Assert.assertEquals((Integer) 2, i);
	}
	
	@Test
	public void merge_4() {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("id", 1);
		// 删除
		Integer i = map.merge("id", 99, new BiFunction<Integer, Integer, Integer>() {
			@Override
			public Integer apply(Integer kv, Integer v) {
				return null;
			}
		});
		Assert.assertEquals(null, i);
		Assert.assertEquals((Integer) 0,  (Integer) map.size());
	}
	
	private Integer replace(Integer kv, Integer v) {
		return kv + v;
	}
}
