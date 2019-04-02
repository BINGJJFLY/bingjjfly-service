package com.wjz.demo.java.map.hashmap;

import java.util.Map;
import java.util.Objects;

/**
 * 单向链表结构
 * 
 * @author iss002
 *
 * @param <K>
 * @param <V>
 */
public class HashMapNode<K, V> {
	
	public static class Node<K, V> implements Map.Entry<K, V> {

		final int hash;
		final K key;
		V value;
		public Node<K, V> next;

		public Node(int hash, K key, V value, Node<K, V> next) {
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}

		@Override
		public final K getKey() {
			return key;
		}

		@Override
		public final V getValue() {
			return value;
		}

		@Override
		public final V setValue(V value) {
			V old = value;
			this.value = value;
			return old;
		}

		public final int hashCode() {
			return Objects.hashCode(key) ^ Objects.hashCode(value);
		}

	}
	
}
