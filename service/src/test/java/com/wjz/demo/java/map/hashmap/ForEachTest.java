package com.wjz.demo.java.map.hashmap;

import java.util.HashMap;
import java.util.function.BiConsumer;

import org.junit.Test;

public class ForEachTest {
	
	@Test
	public void forEach_1() {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("id", 1);
		map.put("age", 2);
		// 遍历数组
//		for (int i = 0; i < tab.length; ++i) {
			  // 遍历链条或者是红黑树
//            for (Node<K,V> e = tab[i]; e != null; e = e.next)
//                action.accept(e.key, e.value);
//        }
		map.forEach((k, v) -> System.out.println(k+"-"+v));
	}
	
	@Test
	public void forEach_2() {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("id", 1);
		map.put("age", 2);
		
		map.forEach(new BiConsumer<String, Integer>() {
			@Override
			public void accept(String k, Integer v) {
				println(k, v);
			}
		});
	}

	private void println(String k, Integer v) {
		System.out.println(k+"-"+v);
	}
}
