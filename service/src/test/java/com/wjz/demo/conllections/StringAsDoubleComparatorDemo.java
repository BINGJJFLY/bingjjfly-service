package com.wjz.demo.conllections;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.wjz.demo.Person;
import com.wjz.service.utils.DateUtils;

public class StringAsDoubleComparatorDemo {

	/**
	 * 集合排序 JDK1.8
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		List<Person> list = new ArrayList<>();
		Person person = new Person();
		person.setAsset(12.58);
		person.setAddTime(DateUtils.parse("2018-01-10 12:23:01"));
		person.setName("Aira");
		Person person2 = new Person();
		person2.setAsset(13.12);
		person2.setAddTime(DateUtils.parse("2019-01-10 12:23:01"));
		person2.setName("phone");
		Person person3 = new Person();
		person3.setAsset(3.77);
		person3.setAddTime(DateUtils.parse("2018-09-10 12:23:01"));
		person3.setName("bob");
		Person person4 = new Person();
		person4.setAsset(5.12);
		person4.setAddTime(DateUtils.parse("2017-01-10 12:23:01"));
		person4.setName("Hello");
		
		Collections.addAll(list, person, person2, person3, person4);
		
		for (Person p : list) {
//			System.out.println(p.getAsset());
//			System.out.println(DateUtils.format(p.getAddTime()));
			System.out.println(p.getName());
		}
		
		System.out.println("##########################");
		
//		Collections.sort(list, Comparator.comparingDouble(Person::getAsset));
//		Collections.sort(list, Comparator.comparing(Person::getAddTime));
		Collections.sort(list, Comparator.comparing(Person::getName, String.CASE_INSENSITIVE_ORDER));
		
		for (Person p : list) {
//			System.out.println(p.getAsset());
//			System.out.println(DateUtils.format(p.getAddTime()));
			System.out.println(p.getName());
		}
	}
}
