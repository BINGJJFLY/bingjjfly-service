package com.wjz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.wjz.service.pojo.Person;

import tk.mybatis.mapper.common.Mapper;

public class PersonServiceImpl extends BaseServiceImpl<Person> implements PersonService {

	/**
	 * 构造器方式注入 tk.mybatis.mapper.common.Mapper
	 */
	private PersonMapper<Person> personMapper;

	@Autowired
	public PersonServiceImpl(Mapper<Person> personMapper) {
		super(personMapper);
	}

	@Override
	public Person selectById(Integer id) {
		return personMapper.selectByPrimaryKey(id);
	}

	private interface PersonMapper<T> extends Mapper<T> {

	}

}
