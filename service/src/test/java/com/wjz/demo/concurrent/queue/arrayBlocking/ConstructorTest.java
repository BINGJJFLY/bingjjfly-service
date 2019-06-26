package com.wjz.demo.concurrent.queue.arrayBlocking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Test;

/**
 * 有界数组阻塞队列
 * 适用于指定数量任务的生产消费模式
 * removeAt时会涉及数组移动
 * 添加删除元素，查询元素个数，查询元素是否存在均需要加锁，并发性较差
 *
 * @author iss002
 *
 */
public class ConstructorTest {
	
	@Test
	public void capacity() {
		// 数组阻塞队列必须指定容量大小
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
	}
	
	@Test
	public void capacityAndLock() {
		// 数组阻塞队列必须指定容量大小
		// 指定重进入锁的公平性（不指定默认使用非公平性锁）
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10, false);
	}

	/*
	 public ArrayBlockingQueue(int capacity, boolean fair) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        $* 成员变量被final修饰，构造函数内必须初始化 *$
        this.items = new Object[capacity];
        lock = new ReentrantLock(fair);
        $* 数组不空的锁条件 *$
        notEmpty = lock.newCondition();
        $* 数组不满的锁条件 *$
        notFull =  lock.newCondition();
     }
	 */
	
	@Test
	public void capacityAndLockAndCollection() {
		Collection<String> c = new ArrayList<>();
		// 指定容器大小、重进入锁的公平性、初始集合
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10, false, c);
	}
	
	/*
	 public ArrayBlockingQueue(int capacity, boolean fair,
                              Collection<? extends E> c) {
        this(capacity, fair);

        final ReentrantLock lock = this.lock;
        $* 锁仅用于可见性而不是互斥 *$
        lock.lock(); // Lock only for visibility, not mutual exclusion
        try {
            int i = 0;
            try {
                for (E e : c) {
                    checkNotNull(e);
                    items[i++] = e;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new IllegalArgumentException();
            }
            count = i;
            $* 数组容量是指定大小的，数组只能重复利用 *$
            putIndex = (i == capacity) ? 0 : i;
        } finally {
            lock.unlock();
        }
     }
	 */
}
