package com.wjz.demo.java.map.linkedhashmap;

import java.util.LinkedHashMap;

import org.junit.Test;

/**
 * LinkedHashMap获得元素
 * 
 * @author iss002
 *
 */
public class GetTest {
	
	@Test
	public void get() {
		LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
		map.put("id", 1);
		
		map.get("id");
	}

	/*
	public V get(Object key) {
        Node<K,V> e;
        $** 调用父类的getNode()获得节点 **$
        if ((e = getNode(hash(key), key)) == null)
            return null;
        if (accessOrder)
        	$** 获得指定元素后的操作，将节点移动到链表的尾端（删除时最后删除） **$
            afterNodeAccess(e);
        return e.value;
    }
    */
	
	@Test
	public void get_2() {
		LinkedHashMap<String, Integer> map = new LinkedHashMap<>(16, 0.75f, true);
		map.put("id", 1);
		map.put("age", 2);
		map.put("assert", 3);
		
		// 返回指定节点的值，并将节点置于链表的尾端
		// 类似于缓存过期策略LRU（最近最少使用）
		map.get("id");
	}
	
	/*
	void afterNodeAccess(Node<K,V> e) { // move node to last
        LinkedHashMap.Entry<K,V> last;
        if (accessOrder && (last = tail) != e) {
        	$** p为e，b为p前一个节点，a为p的后一个节点 **$
            LinkedHashMap.Entry<K,V> p =
                (LinkedHashMap.Entry<K,V>)e, b = p.before, a = p.after;
            p.after = null;
            if (b == null)
                head = a;
            else
                b.after = a;
            if (a != null)
                a.before = b;
            else
                last = b;
            if (last == null)
                head = p;
            else {
                p.before = last;
                last.after = p;
            }
            tail = p;
            ++modCount;
        }
    }
	*/
	
	@Test
	public void getOrDefault() {
		LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
		
		map.getOrDefault("id", 1);
	}
}
