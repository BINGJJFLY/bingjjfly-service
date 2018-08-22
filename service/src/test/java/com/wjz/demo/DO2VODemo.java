package com.wjz.demo;

import com.wjz.service.pojo.Person;
import com.wjz.service.vo.PersonVO;
import com.wjz.service.vo.TypeCastFactory;

public class DO2VODemo {

	public static void main(String[] args) {
		Person person = new Person();
		TypeCastFactory.create(person, PersonVO.class);
	}
}
