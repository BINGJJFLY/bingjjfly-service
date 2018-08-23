package com.wjz.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MetaDemo {

	public static void main(String[] args) {
		Date now = new Date();
		List<String> list = new ArrayList<>();
		list.add("hello");
		list.add("world");
		List<String> list2 = new ArrayList<>();
		list2.add("hello");
		list2.add("world");
		
		Person person = new Person();
		person.setId(18);
		person.setName("wjz");
		person.setAddTime(now);
		person.setAsset(88D);
		person.setUsed(true);
		person.setList(list);
		System.out.println(person.hashCode());
		System.out.println(person);
		
		Person person2 = new Person();
		person2.setId(18);
		person2.setName("wjz");
		person2.setAddTime(now);
		person2.setAsset(88D);
		person2.setUsed(true);
		person2.setList(list2);
		System.out.println(person2.hashCode());
		System.out.println(person2);
		
		System.out.println("result is : " + person.equals(person2));
		
	}
}
