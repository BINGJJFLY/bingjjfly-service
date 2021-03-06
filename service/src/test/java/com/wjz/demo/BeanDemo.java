package com.wjz.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wjz.service.pojo.Person;

public class BeanDemo {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>();
		list.add("hello");
		list.add("world");
		List<String> list2 = new ArrayList<>();
		list2.add("hello");
		list2.add("world");
		
		Date now = new Date();
		
		Person person = new Person();
		person.setId(1);
		person.setName("王金钊");
		person.setAsset(88.88);
		person.setAddTime(now);
		person.setList(list);
		System.out.println(person.hashCode());
		
		Person person2 = new Person();
		person2.setId(1);
		person2.setName("王金钊");
		person2.setAsset(88.88);
		person2.setAddTime(now);
		person2.setList(list2);
		System.out.println(person2.hashCode());
		
		System.out.println(person.equals(person2));
	}
}
