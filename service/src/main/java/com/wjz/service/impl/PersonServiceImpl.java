package com.wjz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjz.service.pojo.Person;

@Transactional
@Service("personService")
public class PersonServiceImpl extends BaseServiceImpl<Person, PersonMapper> implements PersonService {

	/**
	 * 构造器方式注入 tk.mybatis.mapper.common.Mapper
	 */
	@Autowired
	public PersonServiceImpl(PersonMapper personMapper) {
		super(personMapper);
	}

	@Override
	public Person selectById(Integer id) {
		return selectByPrimaryKey(id);
	}

	@Override
	public Person selectByName(String name) {
		return mapper.selectByName(name);
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

}
