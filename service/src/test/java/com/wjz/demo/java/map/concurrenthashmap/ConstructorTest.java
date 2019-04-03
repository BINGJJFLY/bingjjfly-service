package com.wjz.demo.java.map.concurrenthashmap;

/**
 * 并发的HashMap
 * 
 * <p>
 * 在多线程环境下使用HashMap进行put操作时可能会引起死循环
 * 原因是HashMap的Entry链表形成环，Entry的next节点永远不为空产生死循环
 * </p>
 * <p>
 * Hashtable效率低下：其public方法均用synchronize修饰， 
 * 这表示在多线程操作时，每个线程在操作之前都会锁住整个map，待操作完成后才释放
 * </p>
 * <p>
 * HashTable在竞争激烈的并发环境下表现出效率低下的原因是所有访问HashTable的线程都必须竞争同一把锁
 * 锁分段技术：
 * 	首先将数据分成一段一段地存储
 * 	然后给每一段数据配一把锁
 * 	当一个线程占用锁访问其中一个段数据的时候，其他段的数据也能被其他线程访问
 * </p>
 * 
 * @author iss002
 *
 */
public class ConstructorTest {

}
