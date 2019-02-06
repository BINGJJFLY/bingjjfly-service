package com.wjz.service.impl;

import com.wjz.service.pojo.Person;

import tk.mybatis.mapper.common.Mapper;

public interface PersonMapper extends Mapper<Person> {
	
	Person selectByName(String name);

}
