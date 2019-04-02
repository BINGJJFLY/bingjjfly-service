package com.wjz.demo.java.map.hashmap.tree;

import java.util.HashMap;

import org.junit.Test;

/**
 * 链表结构黑红树结构化
 * 二叉树：
 * 
 * @author iss002
 *
 */
public class TreeifyTest {
	
	@Test
	public void treeify() {
		
		HashMap<String, Integer> map = new HashMap<>();
		
		// 数组要求达到64，所以达到阈值24的时候让其扩容至64
		for (int i = 0; i < 25; i++) {
			map.put("key_"+i, i);
		}
		
		// 至此数组已经扩容到64，在59索引位填充键值对，8个以上
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
	
	@Test
	public void t() {
		System.out.println(System.identityHashCode("id"));
		System.out.println(System.identityHashCode("id"));
	}
	
	
	/*
	 final void treeifyBin(Node<K,V>[] tab, int hash) {
        int n, index; Node<K,V> e;
        $** hash碰撞如此严重可能时数组长度不够导致的，扩容2倍看效果（链表长度达到8了但是数组长度没有达到64，链表不转红黑树） **$
        if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
            resize();
        $** 确认链表所在索引位不为null **$
        else if ((e = tab[index = (n - 1) & hash]) != null) {
        	$** 定义树节点顶端和根端 **$
            TreeNode<K,V> hd = null, tl = null;
            do {
            	$** 链表结构节点转为红黑树结构节点（顶端节点的前一个节点为null，根端节点的下一个节点为null） **$
            	$** return new TreeNode<>(p.hash, p.key, p.value, next); **$
                TreeNode<K,V> p = replacementTreeNode(e, null);
                $** 如果根端为null则设置顶端为当前树节点 **$
                if (tl == null)
                    hd = p;
                else {
                	$** 每次添加红黑树节点时都是在红黑树结构的根端添加，设置当前节点的上一个节点为根端节点（当前节点设置为根端节点） **$
                    p.prev = tl;
                    $** 根端节点的下一个节点设置为当前节点 **$
                    tl.next = p;
                }
                $** 每次遍历链表节点都会更新红黑树根端节点为当前红黑树节点 **$
                tl = p;
            } while ((e = e.next) != null);
            if ((tab[index] = hd) != null)
            	$** 此处之前仍是链表结构，此处链表结构转换为红黑树结构 **$
                hd.treeify(tab);
        }
    }
	 */
	
	/*
	 final void treeify(Node<K,V>[] tab) {
	 		$** 定义树顶端变量节点 **$
            TreeNode<K,V> root = null;
            $** x为当前节点，定义next变量节点但是还没赋初始值，循环结尾将x置为链表头节点的下一个节点（链表头节点-->链表头节点的下一个节点-->链表头节点的下一个节点的下一个节点......） **$
            for (TreeNode<K,V> x = this, next; x != null; x = next) {
                next = (TreeNode<K,V>)x.next;
                x.left = x.right = null;
                if (root == null) {
                	$** 当前节点为链表的头节点即数组位置的节点，此节点设置为树顶端节点 **$
                	$** 当前节点的父节点置为null **$
                    x.parent = null;
                    $** 将当前节点的颜色置为黑色（红黑树顶端节点的颜色为黑色） **$
                    x.red = false;
                    $** 将当前节点置为树的顶端节点 **$
                    root = x;
                }
                $** 如果树的顶端节点不为null **$
                else {
                	$** k,h为当前节点的key和hash **$
                    K k = x.key;
                    int h = x.hash;
                    Class<?> kc = null;
                    for (TreeNode<K,V> p = root;;) {
                        $** dir表示当前节点（x）与树顶端节点（p）的hash值比较，x_h小于p_h则为-1，x_h大于p_h则为1，没有相等的情况要么1要么-1 **$
                        int dir, ph;
                        K pk = p.key;
                        if ((ph = p.hash) > h)
                            dir = -1;
                        else if (ph < h)
                            dir = 1;
                        else if ((kc == null &&
                                  (kc = comparableClassFor(k)) == null) ||
                                 (dir = compareComparables(kc, k, pk)) == 0)
                            dir = tieBreakOrder(k, pk);

                        TreeNode<K,V> xp = p;
                        $** 如果dir小于等于0， **$
                        if ((p = (dir <= 0) ? p.left : p.right) == null) {
                            x.parent = xp;
                            if (dir <= 0)
                                xp.left = x;
                            else
                                xp.right = x;
                            root = balanceInsertion(root, x);
                            break;
                        }
                    }
                }
            }
            moveRootToFront(tab, root);
        }
	 */
	
	/*
	         static <K,V> TreeNode<K,V> balanceInsertion(TreeNode<K,V> root,
                                                    TreeNode<K,V> x) {
            x.red = true;
            for (TreeNode<K,V> xp, xpp, xppl, xppr;;) {
            	$** 当前节点为顶端节点时，返回顶端节点 **$
                if ((xp = x.parent) == null) {
                    x.red = false;
                    return x;
                }
                $** 当前节点为顶端节点的左节点或者是右节点时，返回顶端节点 **$
                else if (!xp.red || (xpp = xp.parent) == null)
                    return root;
                if (xp == (xppl = xpp.left)) {
                    if ((xppr = xpp.right) != null && xppr.red) {
                        xppr.red = false;
                        xp.red = false;
                        xpp.red = true;
                        x = xpp;
                    }
                    else {
                        if (x == xp.right) {
                            root = rotateLeft(root, x = xp);
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        if (xp != null) {
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotateRight(root, xpp);
                            }
                        }
                    }
                }
                else {
                    if (xppl != null && xppl.red) {
                        xppl.red = false;
                        xp.red = false;
                        xpp.red = true;
                        x = xpp;
                    }
                    else {
                        if (x == xp.left) {
                            root = rotateRight(root, x = xp);
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        if (xp != null) {
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotateLeft(root, xpp);
                            }
                        }
                    }
                }
            }
        }
	 */
	
	
	@Test
	public void hash() {
		// **** 0011 1011
		System.out.println(hash("id_1150")+" - "+Integer.toBinaryString(hash("id_1150"))+" - "+(63 & hash("id_1150")));
		System.out.println(hash("id_1271")+" - "+Integer.toBinaryString(hash("id_1271"))+" - "+(63 & hash("id_1271")));
		System.out.println(hash("id_1392")+" - "+Integer.toBinaryString(hash("id_1392"))+" - "+(15 & hash("id_1392")));
		System.out.println(hash("id_2084"));
		System.out.println(hash("id_2480"));
		System.out.println(hash("id_2084") > hash("id_2480"));
	}
	
	@Test
	public void h00111011() {
		String key = "id_";
		for (int i = 0; i < 100000; i++) {
			String k = key.concat(i+"");
			String hashCode = Integer.toBinaryString(hash(k));
			if (hashCode.substring(hashCode.length()-8).equals("00111011")) {
				System.out.println(k);
			}
		}
	}

	static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
