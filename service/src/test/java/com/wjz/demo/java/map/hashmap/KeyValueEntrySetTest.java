package com.wjz.demo.java.map.hashmap;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * HashMap的键、值、键值对集合
 * 
 * @author iss002
 *
 */
public class KeyValueEntrySetTest {
	
	@Test
	public void keySet() {
		HashMap<String, Integer> map = new HashMap<>();
		Set<String> ks = map.keySet();
		Assert.assertEquals(0, ks.size());
	}
	
	Integer[] is = new Integer[] {1, 2, 4, 9, 3};
	
	class IntSet extends AbstractSet<Integer> {
		
		@Override
		public Iterator<Integer> iterator() {
			return new Iterator<Integer>() {
				
				private Iterator<Integer> it = Arrays.asList(is).iterator();
				
				@Override
				public boolean hasNext() {
					return it.hasNext();
				}

				@Override
				public Integer next() {
					return it.next();
				}
			};
		}

		@Override
		public int size() {
			return is.length;
		}
		
	}
	
	@Test
	public void innerSet() {
		Set<Integer> set = new IntSet();
		System.out.println(set);
	}
	
	@Test
	public void forEach_1() {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("id", 1);
		map.put("age", 2);
		map.put("k", 3);
		map.put("assert", 4);
		
		Set<String> ks = map.keySet();
		for (String k : ks) {
			System.out.println(k);
		}
	}

	@Test
	public void forEach_2() {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("id", 1);
		map.put("age", 2);
		map.put("k", 3);
		map.put("assert", 4);
		// 6 --> assert=4; 11 --> id=1; 11 --> k=3; 14 --> age=2;
//		public Set<K> keySet() {
//	        Set<K> ks = keySet;
//	        if (ks == null) {
//	            ks = new KeySet();
//	            keySet = ks;
//	        }
//	        return ks;
//	    }
		Set<String> ks = map.keySet();
		// public final Iterator<K> iterator()     { return new KeyIterator(); }
		Iterator<String> it = ks.iterator();
		while(it.hasNext()) {
			// 索引位从小到大开始遍历数组，如果索引位处有链表或者是红黑树则遍历链表或者是红黑树指导链表尾端或者是红黑树根端
			String key = it.next();
			System.out.println(key+"-"+map.get(key));
		}
	}
	
	/*
	 final class KeyIterator extends HashIterator
       implements Iterator<K> {
       public final K next() { return nextNode().key; }
    } 
	 */
	
	@Test
	public void forEach_3() {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("id", 1);
		map.put("age", 2);
		map.put("k", 3);
		map.put("assert", 4);
		
//		Collection<V> vs = values;
//        if (vs == null) {
//            vs = new Values();
//            values = vs;
//        }
//        return vs;
		Collection<Integer> coll = map.values();
		for (Integer i : coll) {
			System.out.println(i);
		}
	}
	
	/*
	 final class ValueIterator extends HashIterator
       implements Iterator<V> {
       public final V next() { return nextNode().value; }
    }
	 */
	
	@Test
	public void forEach_4() {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("id", 1);
		map.put("age", 2);
		map.put("k", 3);
		map.put("assert", 4);
		
//		Set<Map.Entry<K,V>> es;
//        return (es = entrySet) == null ? (entrySet = new EntrySet()) : es;
		Set<Entry<String, Integer>> enties = map.entrySet();
		for (Entry<String, Integer> entry : enties) {
			System.out.println(entry.getKey()+"-"+entry.getValue());
		}
	}
	
	/*
	 final class EntryIterator extends HashIterator
        implements Iterator<Map.Entry<K,V>> {
        public final Map.Entry<K,V> next() { return nextNode(); }
     }
	 */
	
	/*
	 abstract class HashIterator {
        Node<K,V> next;        // next entry to return
        Node<K,V> current;     // current entry
        int expectedModCount;  // for fast-fail
        int index;             // current slot

        HashIterator() {
            expectedModCount = modCount;
            Node<K,V>[] t = table;
            current = next = null;
            index = 0;
            if (t != null && size > 0) { // advance to first entry
            	$** 无限循环直到index大于等于数组长度或者是t[index++]不等于null **$
            	$** next:id=1,index=12 **$
                do {} while (index < t.length && (next = t[index++]) == null);
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        final Node<K,V> nextNode() {
            Node<K,V>[] t;
            Node<K,V> e = next;
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (e == null)
                throw new NoSuchElementException();
            $** 先遍历数组上的元素，如果元素的下一个节点为null说明该索引位没有链表或者是红黑树，if为true继续遍历数组 **$
            $** 如果元素的下一个节点不为null说明该索引位有链表或者是红黑树，返回链表或者是红黑树的下一个节点 **$
            if ((next = (current = e).next) == null && (t = table) != null) {
                do {} while (index < t.length && (next = t[index++]) == null);
            }
            return e;
        }

        public final void remove() {
            Node<K,V> p = current;
            if (p == null)
                throw new IllegalStateException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            current = null;
            K key = p.key;
            removeNode(hash(key), key, null, false, false);
            expectedModCount = modCount;
        }
    }
	 */
}
