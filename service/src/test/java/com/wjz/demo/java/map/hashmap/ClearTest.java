package com.wjz.demo.java.map.hashmap;

import java.util.HashMap;

import org.junit.Test;

/**
 * 清空HashMap
 * 
 * @author iss002
 *
 */
public class ClearTest {
	
	@Test
	public void clear() {
		HashMap<String, Integer> map = new HashMap<>();
		map.clear();
	}
	
	/*
	 public void clear() {
        Node<K,V>[] tab;
        modCount++;
        $** 如果数组不为null且数组元素大于0 **$
        if ((tab = table) != null && size > 0) {
            $** 遍历数组置设置元素（可能是单节点、链表头端、红黑树顶端）为null，其他对象变为可回收对象 **$
            $** 如果HashMap为一个大对象，则数组长度保留、阈值保留、加载因子保留 **$
            size = 0;
            for (int i = 0; i < tab.length; ++i)
                tab[i] = null;
        }
    }
	 */

}
