package com.wjz.demo.java.set.treeset;

import java.util.Comparator;
import java.util.TreeSet;

import org.junit.Test;

import com.wjz.demo.Person;

/**
 * LinkedHashSet和TreeSet的区别： 
 * 前者保证插入顺序但是不能自动根据元素的值排序 
 * 后者不保证插入顺序但是能自动根据元素的值排序
 * 
 *
 * @author iss002
 *
 */
public class ConstructorTest {

	@Test
	public void general() {
		TreeSet<Integer> set = new TreeSet<>();
		set.add(3);
		set.add(1);
		set.add(5);
		set.add(2);
		set.add(7);
		set.forEach((e) -> System.out.println(e));
	}

	@Test
	public void comparator() {
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
		
		set.forEach((e) -> System.out.println(e.getId()));
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
