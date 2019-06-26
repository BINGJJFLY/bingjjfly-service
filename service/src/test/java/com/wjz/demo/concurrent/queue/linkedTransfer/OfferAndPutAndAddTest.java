package com.wjz.demo.concurrent.queue.linkedTransfer;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class OfferAndPutAndAddTest {
	
	@Test
	public void offer() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		queue.offer("hello");
		// put方法不会阻塞
		queue.put("world");
		queue.offer("bing");
		queue.add("ping");
		queue.offer("iss002", 100, TimeUnit.MILLISECONDS);
	}
	
	/*
	 public boolean offer(E e) {
	 	// 添加元素值，有值，插入元素到队列的尾部不阻塞直接返回，阻塞时长
        xfer(e, true, ASYNC, 0);
        return true;
     }
	 */

	/*
	 private E xfer(E e, boolean haveData, int how, long nanos) {
        if (haveData && (e == null))
            throw new NullPointerException();
        Node s = null;                        // the node to append, if needed

        retry:
        for (;;) {                            // restart on append race
			$* 从head开始向后匹配 *$
            for (Node h = head, p = h; p != null;) { // find & match first node
                boolean isData = p.isData;
                Object item = p.item;
                $* 找到有效节点，进入匹配 *$
                if (item != p && (item != null) == isData) { // unmatched
                	$* 节点与此次操作模式一致，无法匹配 *$
                    if (isData == haveData)   // can't match
                        break;
                    if (p.casItem(item, e)) { // match
                        for (Node q = p; q != h;) {
                            Node n = q.next;  // update by 2 unless singleton
                            if (head == h && casHead(h, n == null ? q : n)) {
                                h.forgetNext();
                                break;
                            }                 // advance and retry
                            if ((h = head)   == null ||
                                (q = h.next) == null || !q.isMatched())
                                break;        // unless slack < 2
                        }
                        LockSupport.unpark(p.waiter);
                        return LinkedTransferQueue.<E>cast(item);
                    }
                }
                Node n = p.next;
                p = (p != n) ? n : (h = head); // Use head if p offlist
            }

			$* 是不是立即返回 *$
            if (how != NOW) {                 // No matches available
                if (s == null)
                	$* 创建新的节点存储元素 *$
                    s = new Node(e, haveData);
                $* 尝试添加到队列尾部 *$
                Node pred = tryAppend(s, haveData);
                if (pred == null)
                    continue retry;           // lost race vs opposite mode
                if (how != ASYNC)
                    return awaitMatch(s, pred, e, (how == TIMED), nanos);
            }
            return e; // not waiting
        }
    }
	 */
	
	/*
	 private Node tryAppend(Node s, boolean haveData) {
        $* 从尾节点开始 *$
        for (Node t = tail, p = t;;) {        // move p to last node and append
            Node n, u;					// temps for reads of next & tail
            $* 如果头节点和尾节点均为null即队列为空 *$   
            if (p == null && (p = head) == null) {
                $* CAS设置头节点 *$
                if (casHead(null, s))
                    return s;                 // initialize
            }
            else if (p.cannotPrecede(haveData))
                return null;                  // lost race vs opposite mode
            $* 不是尾节点继续向后找 *$
            else if ((n = p.next) != null)    // not last; keep traversing
                p = p != t && t != (u = tail) ? (t = u) : // stale tail
                    (p != n) ? n : null;      // restart if off list
            else if (!p.casNext(null, s))
                p = p.next;                   // re-read on CAS failure
            else {
            	$* 松弛度大于等于2时更新tail（当尾节点有两层以上） *$
                if (p != t) {                 // update if slack now >= 2
                    while ((tail != t || !casTail(t, s)) &&
                           (t = tail)   != null &&
                           (s = t.next) != null && // advance and retry
                           (s = s.next) != null && s != t);
                }
                return p;
            }
        }
    }
	 */
}
