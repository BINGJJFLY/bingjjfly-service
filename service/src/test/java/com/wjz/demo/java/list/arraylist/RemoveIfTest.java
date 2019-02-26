package com.wjz.demo.java.list.arraylist;

import java.util.ArrayList;

import org.junit.Test;

/**
 * ArrayListp判断性删除
 * 
 * @author iss002
 *
 */
public class RemoveIfTest {

	@Test
	public void removeif() {
		ArrayList<Person> persons = new ArrayList<>();
		persons.add(new Person("hello", (short) 18));
		persons.add(new Person("world", (short) 3));
		persons.add(new Person("bing", (short) 7));
		persons.add(new Person("ping", (short) 88));
		
		persons.removeIf(person -> person.getAge()>=18);
		persons.forEach(person -> System.out.println(person));
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
