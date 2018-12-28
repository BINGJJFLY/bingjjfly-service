package com.wjz.demo.java;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

public class SortedMapTest {

	@Test
	public void sort() {
		SortedMap<String, Integer> sortedMap = new TreeMap<>();
		sortedMap.put("1", 1);
		sortedMap.put("2", 2);
		sortedMap.put("3", 3);
		sortedMap.put("4", 4);
		sortedMap.put("5", 5);
		
		Map<String, Integer> hashMap = new HashMap<>();
		hashMap.put("bingjjfly", 1);
		hashMap.put("ping", 2);
		hashMap.put("iss002", 3);
		hashMap.put("wang", 4);
		hashMap.put("hello", 5);
		
		for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
		
		for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
	}
}
