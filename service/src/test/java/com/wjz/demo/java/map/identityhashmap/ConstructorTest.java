package com.wjz.demo.java.map.identityhashmap;

import java.util.HashMap;
import java.util.IdentityHashMap;

import org.junit.Test;

/**
 * IdentityHashMap与常用的HashMap的区别:
 * 前者比较key时是“引用相等”(k1.equals(k2) == true)而后者是“对象相等”(k1 == k2)
 * 
 *
 * @author iss002
 *
 */
public class ConstructorTest {
	
	@Test
	public void general() {
		IdentityHashMap<Person, String> map = new IdentityHashMap<>();
		map.put(new Person(1, "iss002"), "1");
		map.put(new Person(1, "iss002"), "2");
		map.forEach((k, v) -> System.out.println(k + ":" + v));
		
		System.out.println("++++++++++++++++++++++++++++++++++++");
		
		HashMap<Person, String> hash_map = new HashMap<>();
		hash_map.put(new Person(1, "iss002"), "1");
		hash_map.put(new Person(1, "iss002"), "2");
		hash_map.forEach((k, v) -> System.out.println(k + ":" + v));
	}

	class Person {
		public Person(Integer id, String name) {
			this.id = id;
			this.name = name;
		}
		
		Integer id;
		String name;
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Person other = (Person) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
		private ConstructorTest getOuterType() {
			return ConstructorTest.this;
		}
	}
}
