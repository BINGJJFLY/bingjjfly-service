package com.wjz.demo.java.map.hashmap;

import java.util.HashMap;

import org.junit.Test;

/**
 * HashMap初始化后首次填充键值对时或者填充时键值对数大有阈值时进行扩容
 * 扩容的都是扩容为原来的容量2倍
 * 
 * @author iss002
 *
 */
public class ResizeTest {
	
	@Test
	public void resize() {
		HashMap<String, Integer> map = new HashMap<>(10, 0.85f);
		// 此时会发生resize
		map.put("id", 1); // 索引为11
		for (int i = 0; i < 12; i++) {
			map.put("id"+i, i);
		}
		// 此时会发生resize 
		map.put("key", 100); // key="id"的索引变为了27
	}
	
/*	
	final Node<K,V>[] resize() {
        Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        $** 如果原数组的容量为0 **$
        if (oldCap > 0) {
        	$** 当键值对数大于等于2的30次方时，阈值设置为整数的最大值（2^31 - 1），返回原节点数组其他不做任何变动 **$
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            $** 如果原容量扩容两倍后小于2的30次方且大于默认的容量16，阈值扩容为原来的两倍 **$
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                     oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }
        $** 原数组的容量为0但是原阈值大于0，新数组的容量设置为原阈值，此时新阈值未被赋值还是0 **$
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        $** HashMap被初始化后首次填充键值对，原阈值为0表示使用默认值 **$
        else {               // zero initial threshold signifies using defaults
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        $** 最新阈值为0 **$
        if (newThr == 0) {
        	$** 计算最新的阈值 **$
            float ft = (float)newCap * loadFactor;
            $** 最新的容量和阈值都小于2的30次方，最新的阈值为上面计算得到的值，否则为整数的最大值 **$
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                      (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        $** 以扩容后的容量值创建新的数组 **$
        @SuppressWarnings({"rawtypes","unchecked"})
            Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        $** HashMap的数组设置为新创建的数组 **$
        table = newTab;
        $** HashMap已经填充过键值对了 **$
        if (oldTab != null) {
        	$** 遍历旧数组的各个元素其中在特定索引位上可能有链条结构存在，为新的数组赋值 **$
            for (int j = 0; j < oldCap; ++j) {
                Node<K,V> e;
                if ((e = oldTab[j]) != null) {
                	$** 旧数组当前索引指向的元素设置为null，let gc do its work **$
                    oldTab[j] = null;
                    if (e.next == null)
                    	$** 假如之前的容量值为16，0000 0000 1111 & 1101 0001 1011 --> 0000 0000 1011 = 11，索引为11 **$
                   		$** 假如最新的容量值为32，0000 0001 1111 & 1101 0001 1011 --> 0000 0001 1011 = 27， resize后索引为27 **$
                        newTab[e.hash & (newCap - 1)] = e;
                    $** 如果是红黑树结构的话 **$
                    else if (e instanceof TreeNode)
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    $** 如果是链表结构的话，维持原链表的顺序结构，创建新的链条并为数组赋值 **$
                    else { // preserve order
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        $** 遍历原链条从头到尾，然后创建的链条 **$
                        do {
                            next = e.next;
                            $** 如果节点刚好位于数组的首位，说明节点位于数组的低位 **$
                            if ((e.hash & oldCap) == 0) {
                            	$** 如果低位的尾节点为null **$
                                if (loTail == null)
                                	$** 低位的头节点赋值为当前节点 **$
                                    loHead = e;
                                else
                                	$** 如果低位的尾节点不为null则为低位的尾节点的下一个节点赋值为当前节点 **$
                                    loTail.next = e;
                                $** 无论如何都要为低位尾节点赋值为当前节点 **$
                                loTail = e;
                            }
                            $** 如果节点没有位于数组的首位，说明节点位于数组的高位 **$
                            else {
                            	$** 如果高位的尾节点为null **$
                                if (hiTail == null)
                                	$** 高位的头节点赋值为当前节点 **$
                                    hiHead = e;
                                else
                                	$** 如果高位的尾节点不为null则为高位的尾节点的下一个节点赋值为当前节点 **$
                                    hiTail.next = e;
                                $** 无论如何都要为高位尾节点赋值为当前节点 **$
                                hiTail = e;
                            }
                        $** 一直遍历链条直到到了链条的尾节点 **$
                        } while ((e = next) != null);
                        $** 如果低位的尾节点不为null，也就是说链条位于数组的低位 **$
                        if (loTail != null) {
                        	$** 低位的尾节点的下一个节点设置为null，let gc do its work **$
                            loTail.next = null;
                            $** 新数组的当前索引指向的节点赋值为链条的头节点 **$
                            newTab[j] = loHead;
                        }
                        $** 如果高位的尾节点不为null，也就是说链条位于数组的高位 **$
                        if (hiTail != null) {
                        	$** 高位的尾节点的下一个节点设置为null，let gc do its work **$
                            hiTail.next = null;
                            $** 新数组的当前值加原数组的容量值的索引指向的节点赋值为链条的头节点 **$
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        $** 返回新数组（扩容且原数组元素赋值） **$
        return newTab;
    }
*/
}
