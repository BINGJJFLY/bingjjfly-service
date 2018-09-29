package com.wjz.demo.jndi;

import javax.sql.DataSource;

import org.apache.shiro.jndi.JndiObjectFactory;

public class ShiroJndiDemo {

	public static void main(String[] args) {
		JndiObjectFactory<DataSource> factory = new JndiObjectFactory<DataSource>();
		factory.setResourceName("Iss002DataSource");
		factory.setRequiredType(DataSource.class);
		factory.setResourceRef(true);
		DataSource dataSource = factory.getInstance();
		System.out.println(dataSource.getClass().getName());
	}
}
