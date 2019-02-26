package com.wjz.demo.java.list.arraylist;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.Test;

/**
 * ArrayList排序
 * 
 * @author iss002
 *
 */
public class SortTest {
	
	@Test
	public void sort() {
		ArrayList<Person> persons = new ArrayList<>();
		persons.add(new Person("hello", (short) 18));
		persons.add(new Person("world", (short) 3));
		persons.add(new Person("bing", (short) 7));
		persons.add(new Person("ping", (short) 88));
		
		// 根据年龄倒序
		persons.sort(Comparator.comparing(Person::getAge).reversed());
		persons.forEach(person -> System.err.println(person));
	}

	class Person {
		public Person(String name, short age) {
			this.name = name;
			this.age = age;
		}
		String name;
		short age;

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public short getAge() {
			return age;
		}
		public void setAge(short age) {
			this.age = age;
		}
		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + "]";
		}
	}
}
