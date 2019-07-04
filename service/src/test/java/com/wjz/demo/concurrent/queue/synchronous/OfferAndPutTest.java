package com.wjz.demo.concurrent.queue.synchronous;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

public class OfferAndPutTest {
	
	@Test
	public void offer() {
		SynchronousQueue<String> queue = new SynchronousQueue<>();
		// 如果另一个线程正在等待接收指定的元素，则将其插入此队列
		Assert.assertEquals(false, queue.offer("hello"));
	}
	
	@Test
	public void offer2() {
		SynchronousQueue<String> queue = new SynchronousQueue<>();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					queue.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 已经有等待获取的线程，添加时唤醒等待线程，删除头节点（take时填充了一个头节点）
		Assert.assertEquals(true, queue.offer("hello"));
	}
	
	@Test
	public void offer3() {
		SynchronousQueue<String> queue = new SynchronousQueue<>();
		try {
			// 添加后（添加头节点），如果超过指定时间后仍没有现成要获取元素，删除头节点，添加失败
			Assert.assertEquals(false, queue.offer("hello", 2000, TimeUnit.MILLISECONDS));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 public boolean offer(E e) {
        if (e == null) throw new NullPointerException();
        return transferer.transfer(e, true, 0) != null;
     }
	 */
	
	/*
	 @SuppressWarnings("unchecked")
        E transfer(E e, boolean timed, long nanos) {
            
             * Basic algorithm is to loop trying one of three actions:
             *
             * 1. If apparently empty or already containing nodes of same
             *    mode, try to push node on stack and wait for a match,
             *    returning it, or null if cancelled.
             *
             * 2. If apparently containing node of complementary mode,
             *    try to push a fulfilling node on to stack, match
             *    with corresponding waiting node, pop both from
             *    stack, and return matched item. The matching or
             *    unlinking might not actually be necessary because of
             *    other threads performing action 3:
             *
             * 3. If top of stack already holds another fulfilling node,
             *    help it out by doing its match and/or pop
             *    operations, and then continue. The code for helping
             *    is essentially the same as for fulfilling, except
             *    that it doesn't return the item.
             

            SNode s = null; // constructed/reused as needed
            int mode = (e == null) ? REQUEST : DATA;

            for (;;) {
                SNode h = head;
                $* 头节点是否为null，头节点操作和当前操作是否相同 *$
                if (h == null || h.mode == mode) {  // empty or same-mode
                    $* timed为true且nanos小于等于0不阻塞线程 *$
                    if (timed && nanos <= 0) {      // can't wait
                        if (h != null && h.isCancelled())
                            casHead(h, h.next);     // pop cancelled node
                        else
                            return null;
                    $* 创建一个节点，设置为头节点 *$
                    } else if (casHead(h, s = snode(s, e, h, mode))) {
                    	$* 自旋特定次数或者是阻塞，直到节点完成操作匹配，超时添加仍未完成匹配取消添加任务 *$
                        SNode m = awaitFulfill(s, timed, nanos);
                        if (m == s) {               // wait was cancelled
                            clean(s);
                            return null;
                        }
                        if ((h = head) != null && h.next == s)
                            casHead(h, s.next);     // help s's fulfiller
                        return (E) ((mode == REQUEST) ? m.item : s.item);
                    }
                $* 是否完成匹配操作 *$
                } else if (!isFulfilling(h.mode)) { // try to fulfill
                    if (h.isCancelled())            // already cancelled
                        casHead(h, h.next);         // pop and retry
                    $* 创建一个节点，将头节点设置为其下一个节点，并将其设置为头节点 *$
                    else if (casHead(h, s=snode(s, e, h, FULFILLING|mode))) {
                        for (;;) { // loop until matched or waiters disappear
                            SNode m = s.next;       // m is s's match
                            if (m == null) {        // all waiters are gone
                                casHead(s, null);   // pop fulfill node
                                s = null;           // use new node next time
                                break;              // restart main loop
                            }
                            SNode mn = m.next;
                            $* 尝试匹配 *$
                            if (m.tryMatch(s)) {
                                casHead(s, mn);     // pop both s and m
                                return (E) ((mode == REQUEST) ? m.item : s.item);
                            } else                  // lost match
                                s.casNext(m, mn);   // help unlink
                        }
                    }
                } else {                            // help a fulfiller
                    SNode m = h.next;               // m is h's match
                    if (m == null)                  // waiter is gone
                        casHead(h, null);           // pop fulfilling node
                    else {
                        SNode mn = m.next;
                        if (m.tryMatch(h))          // help match
                            casHead(h, mn);         // pop both h and m
                        else                        // lost match
                            h.casNext(m, mn);       // help unlink
                    }
                }
            }
        }
	 */
	
	@Test
	public void put() {
		SynchronousQueue<String> queue = new SynchronousQueue<>();
		try {
			// 当没有其他线程正在等待获取元素时，填充头节点，自旋32/512次（赌博），仍没有线程要获取元素，则阻塞当前线程
			queue.put("hello");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void putAndOffer() {
		SynchronousQueue<String> queue = new SynchronousQueue<>();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					queue.put("hello");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// put线程填充头节点，头节点不为空但是put和offer是相同操作，所以添加元素失败
		Assert.assertEquals(false, queue.offer("world"));
	}

}
