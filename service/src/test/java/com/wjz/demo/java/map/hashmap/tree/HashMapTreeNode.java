package com.wjz.demo.java.map.hashmap.tree;

import com.wjz.demo.java.map.hashmap.HashMapNode;
import com.wjz.demo.java.map.hashmap.HashMapNode.Node;
import com.wjz.demo.java.map.linkedhashmap.LinkedHashMapEntry;

public class HashMapTreeNode<K, V> {
	
	public static class TreeNode<K, V> extends LinkedHashMapEntry.Entry<K, V> {
		
		TreeNode<K, V> parent; // red-block tree links 红黑树的连接点
		TreeNode<K, V> left;
		TreeNode<K, V> right;
		TreeNode<K, V> prev; // needed to unlink next upon deletion 删除之后需要断开
		boolean red;

		public TreeNode(int hash, K key, V value, Node<K, V> next) {
			super(hash, key, value, next);
		}
		
		public void treeify(HashMapNode.Node<K, V> tab) {
			for (TreeNode<K, V> x = this, next; x != null; x = next) {
				next = (TreeNode<K, V>) x.next;
			}
			// 等价于
			TreeNode<K, V> a;
			for (TreeNode<K, V> x = this; x != null; x = a) {
				a = (TreeNode<K, V>) x.next;
			}
			// 等价于
			for (TreeNode<K, V> x = this; x != null; x = (TreeNode<K, V>) x.next) {
				
			}
			// 等价于
			for (TreeNode<K, V> x = this, next; x != null;) {
				next = (TreeNode<K, V>) x.next;
				x = next;
			}
			// 等价于
			TreeNode<K, V> b;
			for (TreeNode<K, V> x = this; x != null;) {
				b = (TreeNode<K, V>) x.next;
				x = b;
			}
			
		}

	}
	
}
