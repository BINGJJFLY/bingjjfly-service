package com.wjz.demo.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import org.junit.Assert;
import org.junit.Test;

public class IntegerFieldUpdaterTest {

	@Test
	public void field() {
		User u = new User();
		u.setName("bing");
		u.setAge(18);
		AtomicIntegerFieldUpdater<User> aifu = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
		Assert.assertEquals(18, aifu.getAndIncrement(u));
		Assert.assertEquals(19, aifu.get(u));
	}
	
	class User {
		String name;
		volatile int age;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		@Override
		public String toString() {
			return "User [name=" + name + ", age=" + age + "]";
		}
	}
}
