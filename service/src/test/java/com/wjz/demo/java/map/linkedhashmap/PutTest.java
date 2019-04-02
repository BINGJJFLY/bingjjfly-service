package com.wjz.demo.java.map.linkedhashmap;

import java.util.LinkedHashMap;

import org.junit.Test;

/**
 * LinkedHashMap填充键值对
 * 
 * @author iss002
 *
 */
public class PutTest {
	
	@Test
	public void put() {
		LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
		
		// 调用父类的put方法，newNode时会创建LinkedHashMap.Entry，并将元素置于链表尾端
		map.put("id", 1);
	}
	
	/*
	 Node<K,V> newNode(int hash, K key, V value, Node<K,V> e) {
        LinkedHashMap.Entry<K,V> p =
            new LinkedHashMap.Entry<K,V>(hash, key, value, e);
        $** 将元素置于链表的尾端 **$
        linkNodeLast(p);
        return p;
     }
	 */
	
	/*
	 TreeNode<K,V> newTreeNode(int hash, K key, V value, Node<K,V> next) {
        TreeNode<K,V> p = new TreeNode<K,V>(hash, key, value, next);
        $** 将元素置于链表的尾端 **$
        linkNodeLast(p);
        return p;
     }
	 */
	
	/*
	 private void linkNodeLast(LinkedHashMap.Entry<K,V> p) {
        LinkedHashMap.Entry<K,V> last = tail;
        tail = p;
        if (last == null)
            head = p;
        else {
            p.before = last;
            last.after = p;
        }
     }
	 */

	@Test
	public void treeify() {
		LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
		for (int i = 0; i < 25; i++) {
			map.put("key_"+i, i);
		}
		
		// 红黑树型化结构，LinkedHashMap采用的结构为数组+链表+红黑树
		map.put("id_1150", 1);
		map.put("id_1271", 2);
		map.put("id_1392", 3);
		map.put("id_2084", 4);
		map.put("id_2480", 5);
		map.put("id_3052", 6);
		map.put("id_3173", 7);
		map.put("id_3294", 8);
		map.put("id_3690", 9);
	}
	
}
