package com.wjz.demo.concurrent;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * 锁降级
 *
 * @author iss002
 *
 */
public class LockDemote {
	
	ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	ReadLock readLock = lock.readLock();
	WriteLock writeLock = lock.writeLock();
	boolean latest = true;
	
	public void processData() {
		readLock.lock();
		if (!latest) {
			// 必须先释放读锁，不释放读锁的话是无法获取写锁的
			readLock.unlock();
			// 锁降级从写锁取到开始，获得写锁之后，其他线程会被阻塞在读锁和写锁的lock()方法上
			writeLock.lock();
			try {
				if (!latest) {
					// 数据更新中
					latest = true;
				}
				// 数据已经是最新的，再次获取读锁，这样即使数据过期之后也不能获得写锁，保证此次读取数据的正确性
				readLock.lock();
			} finally {
				writeLock.unlock();
			}
			// 锁降级完成，写锁降级为读锁
		}
		try {
			// 使用最新的数据中
		} finally {
			readLock.unlock();
		}
	}

}
