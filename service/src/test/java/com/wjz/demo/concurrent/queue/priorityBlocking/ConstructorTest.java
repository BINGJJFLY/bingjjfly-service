package com.wjz.demo.concurrent.queue.priorityBlocking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.concurrent.PriorityBlockingQueue;

import org.junit.Test;

import com.wjz.demo.Person;

public class ConstructorTest {
	
	@Test
	public void capacity() {
		// 默认容量11
		PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>();
		// 指定容量
		PriorityBlockingQueue<String> queue_2 = new PriorityBlockingQueue<>(10);
	}

	/*
	 public PriorityBlockingQueue(int initialCapacity,
                                 Comparator<? super E> comparator) {
        if (initialCapacity < 1)
            throw new IllegalArgumentException();
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.comparator = comparator;
        this.queue = new Object[initialCapacity];
    }
	 */
	
	@Test
	public void collection() {
		Collection<Integer> c = new ArrayList<>();
		c.add(4);
		c.add(2);
		c.add(7);
		c.add(1);
		PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>(c);
		for (Integer i : queue) {
			System.out.println(i);
		}
	}
	
	@Test
	public void sortedSet() {
		TreeSet<Person> set = new TreeSet<>(new PersonComparator());
		Person p1 = new Person();
		p1.setId(7);
		Person p2 = new Person();
		p2.setId(2);
		Person p3 = new Person();
		p3.setId(5);
		Person p4 = new Person();
		p4.setId(4);
		set.add(p1);
		set.add(p2);
		set.add(p3);
		set.add(p4);
		
		PriorityBlockingQueue<Person> queue = new PriorityBlockingQueue<>(set);
		for (Person p : queue) {
			System.out.println(p.getId());
		}
	}
	
	class PersonComparator implements Comparator<Person> {

		@Override
		public int compare(Person o1, Person o2) {
			int i = o1.getId() - o2.getId();
			if (i > 0) {
				return 1;
			} else if (i == 0) {
				return 0;
			} 
			return -1;
		}

	}
}
