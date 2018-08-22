package com.wjz.demo;

import java.util.Date;

import com.wjz.service.pojo.Person;

public class BeanDemo {

	public static void main(String[] args) {
		Person person = new Person();
		person.setId(1);
		person.setName("王金钊");
		person.setAsset(88.88);
		person.setAddTime(new Date());
		
		System.out.println(person);
	}
}
