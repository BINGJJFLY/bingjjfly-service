package com.wjz.demo.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionUseCase {

	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();

	public void await() throws InterruptedException {
		lock.lock();
		try {
			// 当前线程会释放锁并在此等候
			// 其他的线程调用Condition.signal()方法，通知当前线程，当前线程从await()方法返回
			// 并且在返回前已经获取了锁
			condition.await();
		} finally {
			lock.unlock();
		}
	}

	public void singal() {
		lock.lock();
		try {
			condition.signal();
		} finally {
			lock.unlock();
		}
	}

}
