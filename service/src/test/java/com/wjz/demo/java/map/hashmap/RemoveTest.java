package com.wjz.demo.java.map.hashmap;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

/**
 * HashMap的删除
 * 
 * @author iss002
 *
 */
public class RemoveTest {
	
	@Test
	public void remove() {
		HashMap<String, Integer> map = new HashMap<>(2, 1);
//		Node<K,V> e;
//        return (e = removeNode(hash(key), key, null, false, true)) == null ?
//            null : e.value;
		// 计算key的hash值
		map.put("id", 1);
		map.put("k", 2);
		// Removes the mapping for the specified key from this map if present.
		// 这里只是删除了节点在HashMap中链表的引用删除掉，节点本身没有被置为null，但是没有引用的对象也即将被GC处理掉
		// （ 如果一个对象到GC Roots没有任何引用链相连接时，则证明对象时不可用的即可回收对象 ）
		Assert.assertEquals((Integer)2, map.remove("k"));
	}

	/*
	 final Node<K,V> removeNode(int hash, Object key, Object value,
                               boolean matchValue, boolean movable) {
        Node<K,V>[] tab; Node<K,V> p; int n, index;
        $** HashMap的数组不为null且长度不为0且计算出索引值指向的数组元素不为null **$
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (p = tab[index = (n - 1) & hash]) != null) {
            Node<K,V> node = null, e; K k; V v;
            $** key的hash值和数组元素的hash值相等且key值相等，也就是节点刚好位于链表头端 **$
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                node = p;
            $** 如果节点不是位于链表的头端则需要遍历链表 **$
            else if ((e = p.next) != null) {
            	$** 如果是红黑树结构 **$
                if (p instanceof TreeNode)
                    node = ((TreeNode<K,V>)p).getTreeNode(hash, key);
                $** 如果是链表结构 **$
                else {
                	$** 从头到尾遍历链表，直到找到key的hash值和链表节点的hash值相等且key值相等的节点，或者是遍历到链表尾端也没有找到 **$
                    do {
                        if (e.hash == hash &&
                            ((k = e.key) == key ||
                             (key != null && key.equals(k)))) {
                            node = e;
                            break;
                        }
                        $** p节点为e节点的上一个节点 **$
                        p = e;
                    } while ((e = e.next) != null);
                }
            }
            if (node != null && (!matchValue || (v = node.value) == value ||
                                 (value != null && value.equals(v)))) {
                $** 目标节点结构位于红黑树结构上 **$
                if (node instanceof TreeNode)
                    ((TreeNode<K,V>)node).removeTreeNode(this, tab, movable);
                $** 目标节点位于数组结构上 **$
                else if (node == p)
                	$** 数组上的目标元素设置为节点的下一个节点的值即null **$
                    tab[index] = node.next;
                $** 目标节点位于链表结构上 **$
                else
                	$** 目标节点的上一个节点设置为目标节点的下一个节点，这里只是删除了节点在HashMap中链表的引用删除掉，节点本身没有被置为null **$
                    p.next = node.next;
                ++modCount;
                --size;
                afterNodeRemoval(node);
                return node;
            }
        }
        return null;
    }
	*/
	
	@Test
	public void removeMatchValue() {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("id", 1);
		map.put("age", 2);
		map.remove("id");
		Assert.assertEquals(1, map.size());
		map.remove("age", 1);
		Assert.assertEquals(1, map.size());
		map.remove("age", 2);
		Assert.assertEquals(0, map.size());
	}
}
