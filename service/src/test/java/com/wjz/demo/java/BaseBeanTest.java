package com.wjz.demo.java;

import java.time.Month;
import java.util.Date;

import org.junit.Test;

import com.wjz.demo.domain.User;

public class BaseBeanTest {
	
	@Test
	public void tostring() {
		User user = new User();
		user.setId(8);
		user.setName("iss002");
		user.setAsset(77.77);
		user.setAddTime(new Date());
		
		System.out.println(user);
	}
	
	@Test
	public void hashcode() {
		User user = new User();
		user.setId(8);
		user.setName("iss002");
		user.setAsset(77.77);
		user.setAddTime(new Date());
		
		System.out.println(user.hashCode());
	}
	
	@Test
	public void equals() {
		User user = new User();
		user.setId(8);
		user.setName("iss002");
		user.setAsset(77.77);
		user.setAddTime(new Date());
		
		User user2 = new User();
		user2.setId(8);
		user2.setName("iss002");
		user2.setAsset(77.77);
		user2.setAddTime(new Date());
		
		User user3 = new User();
		user3.setId(8);
		user3.setName("iss002");
		user3.setAsset(77.78);
		user3.setMonth(Month.JANUARY);
		user3.setSons(null);
		user3.setAddTime(new Date());
		
		System.out.println(user.equals(user));
		System.out.println(user.equals(user2));
		System.out.println(user.equals(user3));
	}

}
