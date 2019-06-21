package com.wjz.demo.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有界队列 FIFO
 *
 * @author iss002
 *
 * @param <T>
 */
public class BoundedQueue<T> {

	private Object[] items;
	private int count, removeIndex, addIndex;
	private ReentrantLock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();

	public BoundedQueue(int size) {
		items = new Object[size];
	}

	public void add(T item) {
		lock.lock();
		try {
			while (count == items.length) {
				try {
					System.out.println("队列已满添加阻塞-" + Thread.currentThread().getName());
					notFull.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("[" + addIndex + "]添加元素[" + item + "]");
			items[addIndex++] = item;
			if (addIndex == items.length) {
				addIndex = 0;
			}
			count++;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	@SuppressWarnings("unchecked")
	public T remove() {
		lock.lock();
		try {
			while (count == 0) {
				try {
					System.out.println("队列已空删除阻塞-" + Thread.currentThread().getName());
					notEmpty.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Object result = items[removeIndex];
			System.out.println("[" + removeIndex + "]删除元素[" + result + "]");
			items[removeIndex] = null;
			if (++removeIndex == items.length) {
				removeIndex = 0;
			}
			count--;
			notFull.signal();
			return (T) result;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		BoundedQueue<String> queue = new BoundedQueue<>(10);
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 3; j++) {
						queue.add(Thread.currentThread().getName() + "-" + j);
					}
				}
			}, "Thread-Add-" + i);
			t.start();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 3; j++) {
						queue.remove();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}, "Thread-Add-" + i);
			t.start();
		}
		try {
			Thread.sleep(9999999);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
