package com.wjz.demo.java.map.linkedhashmap;

import com.wjz.demo.java.map.hashmap.HashMapNode;

public class LinkedHashMapEntry<K, V> {
	
	public static class Entry<K, V> extends HashMapNode.Node<K, V> {
		
		Entry<K, V> before, after;

		public Entry(int hash, K key, V value, HashMapNode.Node<K, V> next) {
			super(hash, key, value, next);
		}

	}
	
}
