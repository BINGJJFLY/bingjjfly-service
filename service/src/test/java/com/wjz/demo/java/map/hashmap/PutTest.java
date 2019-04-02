package com.wjz.demo.java.map.hashmap;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

public class PutTest {
	
	@Test
	public void put() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", 1);
		map.put("k", 2);
		// return putVal(hash(key), key, value, false, true);
		map.put("g", 3);
	}
	
	@Test
	public void stringHashCode() {
		char[] value = {'i', 'd'};
		int hash = 0;
		int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        System.out.println((int)'i'); // ASCII码对照
        System.out.println((int)'d');
        System.out.println(h);
	}
	
	@Test
	public void hash() {
		String key = "id";
		// java.lang.String的hashCode()复写了Object的hashCode()方法
		System.out.println(key.hashCode()+"-"+Integer.toBinaryString(key.hashCode()));
		System.out.println((key.hashCode() >>> 16)+"-"+Integer.toBinaryString(key.hashCode() >>> 16));
		System.out.println(hash(key)+"-"+Integer.toBinaryString(hash(key)));
	}

	static final int hash(Object key) {
        int h;
        // hash值不超过16位还是原值
        // 1101 0001 1011 ^ 0000 0000 0000 --> 1101 0001 1011 = 3355 （对位相同则为0否则为1）
        // 为什么POJO要重写hashCode就是这个道理，对象当做key的时候必须要重写hashCode()方法
        // key的hash值高位参与运算，减少hash碰撞的可能性
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
	
	@Test
	public void index() {
		int n = 16;
		System.out.println(n + "-" + Integer.toBinaryString(n - 1));
		int h = hash("id");
		System.out.println(h + "-" + Integer.toBinaryString(h));
		// 0000 0000 1111 & 1101 0001 1011 --> 0000 0000 1011 = 11
		// 一个值&另外一个值最大的值为前一个值，这种条件需要满足前一个为(2^n-1)类型
		// 所以这里的n值必须是2的次幂倍，也就是Map的容量值必须是2的次幂倍
		// 这样计算数组的索引时不会数组越界
		int i = (n - 1) & h;
		System.out.println(i);
	}
	
	/*
	 $** onlyIfAbsent if true, don't change existing value，如果为true不替换现有的值 **$
	 $** evict if false, the table is in creation mode. 如果为false链表处于创建模式 **$
	 final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        $** 如果HashMap的数组为null或者是数组的长度为0 **$
        if ((tab = table) == null || (n = tab.length) == 0)
        	$** HashMap进行resize，初始化容量和阈值，创建数组 **$
            n = (tab = resize()).length;
        $** 根据key的hash值计算数组的索引值，如果指定索引对应数组的元素为null **$
        if ((p = tab[i = (n - 1) & hash]) == null)
        	$** 创建节点并为数组赋值 **$
            tab[i] = newNode(hash, key, value, null);
        $** 如果计算的索引对应数组的元素不为null **$
        else {
            Node<K,V> e; K k;
            $** 计算的索引对应的数组的节点的hash和put时key的hash相等且key的值相等，将节点赋值给e **$
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            $** 如果节点是红黑树结构类型的，为红黑树添加节点 **$
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            $** 如果是链表结构，也就是计算的索引值对应的位置上已经有了元素，此时需要进行链表结构存储 **$
            else {
            	$** 无限循环 **$
                for (int binCount = 0; ; ++binCount) {
                	$** 如果节点的下一个节点为null（链表还没有创建形成），直接创建节点并放置在链表的尾端 **$
                    if ((e = p.next) == null) {
                    	$** 创建尾节点（下一个节点为null） 并赋值给节点的下一个节点**$
                        p.next = newNode(hash, key, value, null);
                        $** 如果链表上的节点数大于8时，链表结构转换为红黑树结构 **$
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        $** 下一节点赋值或者是链表结构转换为红黑树结构后跳出无限循环 **$
                        break;
                    }
                    $** 如果数组节点的下一个节点的hash和put时key的hash相等且key也相等（也就是覆盖设置值），跳出无限循环 **$
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    $** 如果节点的下一个节点不为null也不是覆盖设置值，继续无限循环，直到时覆盖设置值或者是到了链表的尾端 **$
                    p = e;
                }
            }
            $** 需要覆盖设置值 **$
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                $** onlyIfAbsent：if true, don't change existing value或者是原值为null时才会赋值 **$
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
	*/
	
	@Test
	public void put_1() {
		// 指定初始容量：table=null,size=0,threshold=1（ tableSizeFor(1) 1的最小二次幂数为1,2^0  ）
		HashMap<String, Integer> map = new HashMap<String, Integer>(1);
		// table=null发生resize，table=[1],size=0,threshold=0（ (int) 1*0.75=0 ）
		// put值时++size>threshold再次发生resize，table=[2]（数组扩容两倍）,size=1,threshold=1 （(int) 2*0.75=1 ）
		map.put("id", 1);
		// put值时++size>threshold再次发生resize，talbe=[4]（数组扩容两倍）,size=2,threshold=3 （(int) 4*0.75=1 ）
		map.put("age", 2);
		// put值时++size==threshold不会发生resize，table=[4],size=3,threshold=3
		map.put("assert", 3);
		// put值时++size>threshold再次发生resize，talbe=[8]（数组扩容两倍）,size=4,threshold=6 （(int) 8*0.75=1 ）
		map.put("amount", 4);
		map.put("key", 5);
		map.put("value", 6);
		//  ++size值大于threshold值时将会再次发生resize
		map.put("resize", 7);
	}
	
	@Test
	public void put_2() {
		// 指定初始容量：table=null,size=0,threshold=2（ tableSizeFor(2) 2的最小二次幂数为2,2^1  ）
		HashMap<String, Integer> map = new HashMap<String, Integer>(2);
		// table=null发生resize，table=[2],size=0,threshold=1（ (int) 2*0.75=1 ）
		// put值时++size==threshold不会发生resize，table=[2],size=1,threshold=1
		map.put("id", 1);
		// put值时++size>threshold再次发生resize，table=[4],size=2,threshold=3（ (int) 4*0.75=3 ）
		map.put("age", 2);
	}
	
	@Test
	public void put_3() {
		// 指定初始容量为3：table=null,size=0,threshold=4（ tableSizeFor(3) 3的最小二次幂数为4,2^2  ）
		// 指定初始容量为4：table=null,size=0,threshold=4（ tableSizeFor(4) 4的最小二次幂数为4,2^2  ）
		HashMap<String, Integer> map = new HashMap<String, Integer>(3);
		// table=null发生resize，table=[4],size=0,threshold=3（ (int) 4*0.75=1 ）
		// put值时++size<threshold不会发生resize，table=[4],size=1,threshold=3
		map.put("id", 1);
	}
	
	@Test
	public void put_4() {
		// 指定初始容量和加载因子：table=null,size=0,loadFactor=1,threshold=2（ tableSizeFor(2) 2的最小二次幂数为2,2^1  ）
		HashMap<String, Integer> map = new HashMap<String, Integer>(2, 1);
		// table=null发生resize，table=[2],size=0,threshold=2（ (int) 2*1=2 ）
		map.put("id", 1);
		// put值时++size==threshold不会发生resize，table=[2],size=2,threshold=2
		map.put("age", 2);
	}
	
	@Test
	public void putAll() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", 1);
		map.put("k", 2);
		for (int i = 0; i < 5; i++) {
			map.put("id_"+i, i);
		}
		
		HashMap<String, Integer> map_2 = new HashMap<String, Integer>();
		map_2.putAll(map);
	}
	
	/*
	 final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict) {
        int s = m.size();
        if (s > 0) {
        	$** HashMap的数组为null时，需要计算阈值 **$
            if (table == null) { // pre-size
            	$** 传入map的size扩容3/4倍加1计算阈值 **$
                float ft = ((float)s / loadFactor) + 1.0F;
                int t = ((ft < (float)MAXIMUM_CAPACITY) ?
                         (int)ft : MAXIMUM_CAPACITY);
                $** 扩容后的size值大于阈值时需要重新计算阈值即扩容后的size值的最小2次幂数 **$
                if (t > threshold)
                    threshold = tableSizeFor(t);
            }
            $** 如果传入的map的size值大于阈值则需要2倍扩容 **$
            else if (s > threshold)
                resize();
            for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
                K key = e.getKey();
                V value = e.getValue();
                $** 如果传入的map的size值很大，则在填充键值对的时候再动态的2倍扩容 **$
                putVal(hash(key), key, value, false, evict);
            }
        }
    }
	*/
	
	@Test
	public void putIfAbsent() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", 1);
		map.putIfAbsent("id", 2);
		Assert.assertEquals((Integer) 1, map.get("id"));
	}
	
	@Test
	public void replace() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", 1);
		Assert.assertEquals((Integer) 1, map.get("id"));
		map.replace("id", 2);
		Assert.assertEquals((Integer) 2, map.get("id"));
		map.replace("id", 3, 3);
		Assert.assertEquals((Integer) 2, map.get("id"));
		map.replace("id", 2, 3);
		Assert.assertEquals((Integer) 3, map.get("id"));
	}
	
}
