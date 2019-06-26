package com.wjz.demo.concurrent.queue.priorityBlocking;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class OfferTest {
	
	@Test
	public void offer() {
		PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>();
		queue.offer("hello");
		queue.offer("world");
		queue.offer("bing");
		queue.offer("iss002");
	}
	
	@Test
	public void offerComparator() {
		PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>(11, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		queue.offer("hello");
		queue.offer("world");
		queue.offer("bing");
		queue.offer("iss002");
		queue.offer("ping");
		
		while (!queue.isEmpty()) {
			try {
				System.out.println(queue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException();
        final ReentrantLock lock = this.lock;
        lock.lock();
        int n, cap;
        Object[] array;
        $* 当元素数大于等于数组长度时扩容 *$
        while ((n = size) >= (cap = (array = queue).length))
            tryGrow(array, cap);
        try {
            Comparator<? super E> cmp = comparator;
            if (cmp == null)
                siftUpComparable(n, e, array);
            $* 如果有比较器，则使用比较器安排元素放置到哪 *$
            else
                siftUpUsingComparator(n, e, array, cmp);
            size = n + 1;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
        return true;
    }
	 */
	
	/*
	 private void tryGrow(Object[] array, int oldCap) {
	 	$* 先释放锁，使用cas控制只有一个线程可以扩容成功，扩容比较耗时如果占锁则其他线程不能出队入队，大大降低并发性 *$
        lock.unlock(); // must release and then re-acquire main lock
        Object[] newArray = null;
        if (allocationSpinLock == 0 &&
            UNSAFE.compareAndSwapInt(this, allocationSpinLockOffset,
                                     0, 1)) {
            try {
                int newCap = oldCap + ((oldCap < 64) ?
                                       (oldCap + 2) : // grow faster if small
                                       (oldCap >> 1));
                if (newCap - MAX_ARRAY_SIZE > 0) {    // possible overflow
                    int minCap = oldCap + 1;
                    if (minCap < 0 || minCap > MAX_ARRAY_SIZE)
                        throw new OutOfMemoryError();
                    newCap = MAX_ARRAY_SIZE;
                }
                if (newCap > oldCap && queue == array)
                    newArray = new Object[newCap];
            } finally {
                allocationSpinLock = 0;
            }
        }
        $* 竞争失败的线程礼让cpu，目的意在让扩容线程扩容后优先获取锁，但是这得不到一定的保证 *$
        if (newArray == null) // back off if another thread is allocating
            Thread.yield();
        lock.lock();
        $* 保证可见性，queue并没有被volatile修饰，加锁保证 *$
        if (newArray != null && queue == array) {
            queue = newArray;
            System.arraycopy(array, 0, newArray, 0, oldCap);
        }
    }
	 */
}
