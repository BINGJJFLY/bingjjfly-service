package com.wjz.demo.java.map.linkedhashmap;

import java.util.LinkedHashMap;

import org.junit.Test;

/**
 * LinkedHashMap检查元素是否存在
 * 
 * @author iss002
 *
 */
public class ContainsValueTest {
	
	@Test
	public void containsValue() {
		LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
		// 从链表的头端一直遍历到链表的尾端直到找到和指定元素值相等的值时返回true
		map.containsValue((Integer) 100);
	}
	
	/*
	public boolean containsValue(Object value) {
        for (LinkedHashMap.Entry<K,V> e = head; e != null; e = e.after) {
            V v = e.value;
            if (v == value || (value != null && value.equals(v)))
                return true;
        }
        return false;
    }
	*/
}
