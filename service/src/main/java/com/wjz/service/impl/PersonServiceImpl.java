package com.wjz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.wjz.service.pojo.Person;

import tk.mybatis.mapper.common.Mapper;

public class PersonServiceImpl extends BaseServiceImpl<Person> implements PersonService {

	/**
	 * 构造器方式注入 tk.mybatis.mapper.common.Mapper
	 */
	@Autowired
	public PersonServiceImpl(PersonMapper<Person> personMapper) {
		super(personMapper);
	}

	@Override
	public Person selectById(Integer id) {
		return selectByPrimaryKey(id);
	}

	@Override
	public int insert(Person person) {
		return insertSelective(person);
	}

	@Override
	public int update(Person person) {
		return updateByPrimaryKeySelective(person);
	}

	@Override
	public int delete(Integer id) {
		return deleteByPrimaryKey(id);
	}
	
	private interface PersonMapper<T> extends Mapper<T> {

	}

}
