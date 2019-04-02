package com.wjz.demo.java.map.hashmap;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

/**
 * HashMap根据key获得value
 * 
 * 没有hash冲突时定位value就是根据hash值索引定位数组元素，非常快
 * 但是有hash冲突，链表结构又很长刚好需要从头遍历到尾那将是非常慢的查询
 * 所以jdk1.8为了优化这种情况引进了红黑树结构
 * 
 * @author iss002
 *
 */
public class GetTest {
	
	@Test
	public void get() {
		HashMap<String, Integer> map = new HashMap<>();
//		Node<K,V> e;
//      return (e = getNode(hash(key), key)) == null ? null : e.value;
		// 首先根据key计算hash值，跟put时计算hash值一样
		map.get("id");
	}

	/*
	final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        $** 如果HashMap的数组不为null且数组容量大于0且通过key的hash计算得出的索引对应的元素不为null **$
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            $** 数组结构，如果hash相等且key相等，返回节点 **$
            if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            $** 链表结构或是红黑树结构 **$
            if ((e = first.next) != null) {
            	$** 红黑树结构 **$
                if (first instanceof TreeNode)
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                $** 链表结构，从头到尾遍历链表 **$
                do {
                	$** 链表上的某一个节点的hash值和key的hash值相等，节点的key值和key值相等，返回该节点 **$
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                $** 如果没有找到节点继续向后遍历链表直到链表尾端 **$
                } while ((e = e.next) != null);
            }
        }
        return null;
    }
	*/
	
	@Test
	public void containsKey() {
		HashMap<String, Integer> map = new HashMap<>();
		// return getNode(hash(key), key) != null;
		// 根据key查找节点，如果节点不为null则key就是存在的
		map.containsKey("id");
	}
	
	@Test
	public void containsValue() {
		HashMap<String, Integer> map = new HashMap<>();
		map.containsValue(1);
	}
	
	/*
	 public boolean containsValue(Object value) {
        Node<K,V>[] tab; V v;
        $** 如果数组为null且元素数大于0 **$
        if ((tab = table) != null && size > 0) {
        	$** 遍历数组 **$
            for (int i = 0; i < tab.length; ++i) {
            	$** 如果数组元素值与目标值不相等则遍历链表或者是红黑树 **$
                for (Node<K,V> e = tab[i]; e != null; e = e.next) {
                    if ((v = e.value) == value ||
                        (value != null && value.equals(v)))
                        return true;
                }
            }
        }
        return false;
    }
	 */
	
	@Test
	public void getOrDefault() {
		HashMap<String, Integer> map = new HashMap<>();
		// Node<K,V> e;
//        return (e = getNode(hash(key), key)) == null ? defaultValue : e.value;
		Assert.assertEquals((Integer) 2, map.getOrDefault("id", 2));
	}
}
