package com.wjz.demo.java.map.hashmap;

import java.util.HashMap;

import org.junit.Test;

/**
 * Hash的构造方法
 * 
 * 加载因子越大，填满的键值对越多，但是hash冲突也会越频繁，添加和查询都会效率低下
 * 加载因子越小，天马的键值对越少，hash冲突也会减少，但是很多空间浪费就多了
 * 牺牲空间换时间的感觉，对内存占用相对较大，但是检索相对较快
 * 为什么hash冲突之后采用链表而不是数组，是因为链表在内存中不是连续存储，可以充分的利用内存空间
 * 
 * @author iss002
 *
 */
public class ConstructorTest {

	@Test
	public void general() {
		// 初始容量为默认值：DEFAULT_INITIAL_CAPACITY; 1 << 4 --> 16
		// 初始化加载因子：this.loadFactor = DEFAULT_LOAD_FACTOR; 0.75f
		// 阈值为0，延迟赋值当put值时发生resize()这时才会为阈值赋值 16 * 0.75 = 12
		HashMap<String, Integer> map = new HashMap<>();
		System.out.println(map);
	}

	@Test
	public void capacity() {
		// 指定初始容量、加载因子、阈值：this(initialCapacity, DEFAULT_LOAD_FACTOR);
		HashMap<String, Integer> map = new HashMap<>(1);
		System.out.println(map);
	}

	@Test
	public void capacityAndloadFactor() {
		// 最小 初始容量不能小于0，最大初始容量不能大于MAXIMUM_CAPACITY; 1 << 30
		// 加载因子不能小于等于0
		HashMap<String, Integer> map = new HashMap<>(10, 0.85f);
	}
	
	@Test
	public void map() {
		HashMap<String, Integer> map = new HashMap<>(10, 0.85f);
		// 初始化加载因子：this.loadFactor = DEFAULT_LOAD_FACTOR;
		// 如果传入Map无键值对则do nothing
		map.put("id", 1);
		// 传入的map的size为1，且map_2无键值对，对map的size扩容3分之4倍加1再取整，然后计算获得阈值
//		if (table == null) { // pre-size
//            float ft = ((float)s / loadFactor) + 1.0F;
//            int t = ((ft < (float)MAXIMUM_CAPACITY) ?
//                     (int)ft : MAXIMUM_CAPACITY);
//            if (t > threshold)
//                threshold = tableSizeFor(t);
//        }
		// 如果map_2有键值对且map的size大于map_2的阈值则resize()也叫re-hash
		// 遍历map为map_2赋值
		HashMap<String, Integer> map_2 = new HashMap<>(map);
		System.out.println(map_2);
	}
	
	@Test
	public void tableSizeFor() {
		// 1 --> 1 --> 2^0
		// 2 --> 2 --> 2^1
		// 3 --> 4 --> 2^2
		// 4 --> 4 --> 2^2
		// 5 --> 8 --> 2^3
		// 9 --> 16 --> 2^4
		// 17 --> 32 --> 2^5
		// initialCapacity的最小二次幂
		System.out.println(tableSizeFor(17));
	}
	
	static final int MAXIMUM_CAPACITY = 1 << 30;
	
	static final int tableSizeFor(int cap) {
        int n = cap - 1;
        // 无符号右移，高位取0，>> 有符号右移，正数高位取0，负数高位取1
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
	
	@Test
	public void expand() {
		// 1 --> 1
		// 2 --> 3
		// 3 --> 5
		// 4 --> 6
		// 5 --> 7
		// 6 --> 9
		// 7 --> 10
		// 8 --> 11
		// 16 --> 22
		// 扩容3分之4倍加1再取整
		float ft = ((float)16 / 0.75f) + 1.0F;
		System.out.println((int)ft);
		System.out.println(tableSizeFor((int)ft));
	}
}
