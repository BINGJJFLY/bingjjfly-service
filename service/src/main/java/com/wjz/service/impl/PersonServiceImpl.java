package com.wjz.service.impl;

import com.wjz.service.bussiness.AbstractBusinessServiceImpl;
import com.wjz.service.bussiness.PersonService;
import com.wjz.service.pojo.Person;

public class PersonServiceImpl extends AbstractBusinessServiceImpl<Person> implements PersonService {

/*  构造器方式注入 tk.mybatis.mapper.common.Mapper
 
	private PersonMapper<Person> personMapper;
	
	@Autowired
	public PersonServiceImpl(Mapper<Person> personMapper) {
		super(personMapper);
	}
*/	

/*  set方式注入 tk.mybatis.mapper.common.Mapper
	
	@Autowired
	private Mapper<Person> personMapper;
	 
	public PersonServiceImpl() {
		setMapper(personMapper);
	}
*/	

/*  构造器方式注入tk.mybatis.mapper.common.Mapper、org.mybatis.spring.SqlSessionFactoryBean
	
	private Mapper<Person> personMapper;
	
	@Autowired
	public PersonServiceImpl(Mapper<Person> personMapper, SqlSessionFactoryBean sqlSessionFactory) {
		super(personMapper, sqlSessionFactory);
	}
*/	
	
/*
	set方式注入tk.mybatis.mapper.common.Mapper、org.mybatis.spring.SqlSessionFactoryBean
	
	@Autowired
	private Mapper<Person> personMapper;
	
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	
	public PersonServiceImpl() {
		setMapper(personMapper);
		setSqlSessionFactory(sqlSessionFactory);
	}
*/
	
/*  构造器方式注入tk.mybatis.mapper.common.Mapper、set方式注入org.mybatis.spring.SqlSessionFactoryBean
	
	private Mapper<Person> personMapper;
	
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	
	@Autowired
	public PersonServiceImpl(Mapper<Person> personMapper) {
		super(personMapper);
		setSqlSessionFactory(sqlSessionFactory);
	}
*/
	
}
