package com.wjz.demo.java.map.hashmap;

import java.util.HashMap;

import org.junit.Test;

/**
 * 克隆HashMap
 * 
 * @author iss002
 *
 */
public class CloneTest {
	
	@Test
	public void cloneTest() {
		HashMap<String, Integer> map = new HashMap<>();
		Object cloner = map.clone();
	}

	/*
	 public Object clone() {
        HashMap<K,V> result;
        try {
            result = (HashMap<K,V>)super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
        $** 各属性置为初始值 **$
        result.reinitialize();
        $** 将本身的HashMap对象作为参数，为新的HashMap对象填充键值对 **$
        result.putMapEntries(this, false);
        return result;
    }
	 */
	
	/*
	 void reinitialize() {
        table = null;
        entrySet = null;
        keySet = null;
        values = null;
        modCount = 0;
        threshold = 0;
        size = 0;
    }
	 */
}
