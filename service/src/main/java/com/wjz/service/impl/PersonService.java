package com.wjz.service.impl;

import com.wjz.service.pojo.Person;

public interface PersonService {
	
	Person selectById(Integer id);
	
	int insert(Person person);
	
	int update(Person person);
	
	int delete(Integer id);

}
