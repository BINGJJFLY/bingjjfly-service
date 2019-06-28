package com.wjz.demo.concurrent.queue.concurrentLinked;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Test;

/**
 * 线程安全的保证元素都能正确的添加到链表中
 *
 * @author iss002
 *
 */
public class OfferTest {
	
	@Test
	public void offer() {
		ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
		queue.offer("hello");
		queue.offer("world");
	}

	/*
	 $* 一： 定位出尾节点；二：为尾节点的下一节点设置为入队节点 *$
	 public boolean offer(E e) {
       checkNotNull(e);
       $* *$
       final Node<E> newNode = new Node<E>(e);
       
		$* 定义变量t，指向tail节点 *$
		$* 定义变量p，表示链表的尾节点（tail节点不一定就是链表的尾节点） *$
		$* 无限循环CAS添加节点，代替加锁同步 *$
       for (Node<E> t = tail, p = t;;) {
       	$* 获得p指向的节点的下一节点（表示获得链表的尾节点的下一个节点，q为空代表p为链表的尾节点，q不为空代表p不是链表的尾节点） *$
           Node<E> q = p.next;
           if (q == null) {
               $* p is last node，p为链表的尾节点，为链表尾节点的下一节点设置为新节点 *$
               if (p.casNext(null, newNode)) {
                   // Successful CAS is the linearization point
                   // for e to become an element of this queue,
                   // and for newNode to become "live".
                   $* tail不是链表的尾节点，每两次更新一次tail *$
                   if (p != t) // hop two nodes at a time
                   	$* tail指向新的节点 *$
                       casTail(t, newNode);  // Failure is OK.
                   return true;
               }
               // Lost CAS race to another thread; re-read next
               $* CAS更新失败，再次尝试 *$
           }
           $* 哨兵节点（next指向了自己），主要表示要删除的节点或者空节点，无法通过next节点获得后续节点 *$
           else if (p == q)
               // We have fallen off list.  If tail is unchanged, it
               // will also be off-list, in which case we need to
               // jump to head, from which all live nodes are always
               // reachable.  Else the new tail is a better bet.
               $* 在执行“!=”时，程序会先取得t的值，在执行t = tail，并取得新的t的值，然后比较这两个值是否相等。 *$
               $* 在单线程中,t != t这种语句显然不会成立，但是在并发环境下，有可能在获得左边的t值后，右边的t值被其他线程修改，这样，t != t 就成立了。 *$
               $* 此处进行了“打赌”，赌尾节点是否被更新过，如果赌赢了则避免了重新查找tail的开销 *$
               p = (t != (t = tail)) ? t : head;
           else
               // Check for tail updates after two hops.
               $* p指向tail节点的下一节点 *$
               $* tail节点被其他线程更新后，p仍指向tail节点 *$
               p = (p != t && t != (t = tail)) ? t : q;
       }
   } 
	 */
}
