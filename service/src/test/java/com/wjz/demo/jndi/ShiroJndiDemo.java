package com.wjz.demo.jndi;

import javax.naming.InitialContext;
import javax.naming.NamingException;
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
		
		try {
			InitialContext context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/Iss002DataSource");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
