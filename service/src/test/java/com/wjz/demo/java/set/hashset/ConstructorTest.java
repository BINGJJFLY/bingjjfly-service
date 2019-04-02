package com.wjz.demo.java.set.hashset;

import java.util.HashSet;

import org.junit.Test;

/**
 * HashSet的构造函数
 * 
 * @author iss002
 *
 */
public class ConstructorTest {
	
	@Test
	public void general() {
		// private transient HashMap<E,Object> map;
		// map = new HashMap<>();
		// HashSet内部维护了一个HashMap，后者不允许有相同的key，HashSet将HashMap的key当做自己的元素
		HashSet<String> set = new HashSet<>();
	}
	
	@Test
	public void capacity() {
		// map = new HashMap<>(10);
		HashSet<String> set = new HashSet<>(10);
	}
	
	@Test
	public void capacityAndloadFactor() {
		// map = new HashMap<>(10, 0.85f);
		HashSet<String> set = new HashSet<>(10, 0.85f);
	}

	@Test
	public void collection() {
		HashSet<String> set = new HashSet<>(10, 0.85f);
		set.add("id");
		
//		map = new HashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
//      addAll(c);
		HashSet<String> set_2 = new HashSet<>(set);
	}
	
	/*
	 public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c)
            if (add(e))
                modified = true;
        return modified;
     }
	 */
}
