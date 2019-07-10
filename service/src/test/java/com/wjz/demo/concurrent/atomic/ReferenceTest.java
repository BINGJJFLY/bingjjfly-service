package com.wjz.demo.concurrent.atomic;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;

public class ReferenceTest {
	
	@Test
	public void reference() {
		User user = new User();
		user.setName("bing");
		user.setAge(18);
		user.setMoney(new BigDecimal("18.73"));
		AtomicReference<User> aru = new AtomicReference<>(user);
		System.out.println(aru.get());
		User user2 = new User();
		user2.setName("ping");
		user2.setAge(28);
		user2.setMoney(new BigDecimal("79.12"));
		System.out.println(aru.getAndSet(user2));
		System.out.println(aru.get());
	}

	class User {
		String name;
		Integer age;
		BigDecimal money;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		public BigDecimal getMoney() {
			return money;
		}
		public void setMoney(BigDecimal money) {
			this.money = money;
		}
		@Override
		public String toString() {
			return "User [name=" + name + ", age=" + age + ", money=" + money + "]";
		}
	}
}
