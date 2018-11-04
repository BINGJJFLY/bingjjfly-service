package com.wjz.demo.factory;

import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.wjz.demo.domain.Son;
import com.wjz.demo.domain.User;
import com.wjz.demo.vo.UserVO;
import com.wjz.service.factory.DefaultViewObjectFactory;
import com.wjz.service.factory.ViewObjectFactory;
import com.wjz.service.vo.handler.CryptoPropertyHandler;

public class ViewObjectFactoryTest {
	
	private User user;
	private ViewObjectFactory factory = new DefaultViewObjectFactory();
	
	@Before
	public void init() {
		user = new User();
		user.setId(8);
		user.setName("iss002");
		user.setAddTime(new Date());
		user.setAsset(883243443237797.86);
	}

	@Test
	public void transformSimpleBean() {
		Object userVO = factory.transform(user);
		System.out.println(userVO);
	}
	
	@Test
	public void transformList() {
		List<User> list = new ArrayList<>();
		User user2 = new User();
		user2.setId(3);
		user2.setName("ping");
		user2.setAddTime(new Date());
		user2.setAsset(7797.86);
		list.add(user);
		list.add(user2);
		Object userVO = factory.transform(list);
		System.out.println(userVO);
	}
	
	@Test
	public void transformMap() {
		Map<String, User> map = new HashMap<>();
		User user2 = new User();
		user2.setId(3);
		user2.setName("ping");
		user2.setAddTime(new Date());
		user2.setAsset(7797.86);
		map.put("user", user);
		map.put("user2", user2);
		Object userVO = factory.transform(map);
		System.out.println(userVO);
	}
	
	@Test
	public void transformBeanWithStringList() {
		List<String> l = new ArrayList<>();
		l.add("hello world");
		user.setList(l);
		Object userVO = factory.transform(user);
		System.out.println(userVO);
	}
	
	@Test
	public void transformBeanWithBeanList() {
		List<Son> sons = new ArrayList<>();
		Son son = new Son();
		son.setId(7L);
		son.setName("bingjjfly");
		son.setStature(77.618D);
		son.setBirthday(new Date());
		sons.add(son);
		user.setSons(sons);
		Object userVO = factory.transform(user);
		System.out.println(userVO);
	}
	
	@Test
	public void transformBeanWithBeanMapList() {
		List<Map<String, User>> list = new ArrayList<>();
		Map<String, User> map = new HashMap<>();
		User user2 = new User();
		user2.setId(3);
		user2.setName("ping");
		user2.setAddTime(new Date());
		user2.setAsset(7797.86);
		map.put("user", user);
		map.put("user2", user2);
		list.add(map);
		Object userVO = factory.transform(list);
		System.out.println(userVO);
	}
	
	@Test
	public void transformBeanWithStringMap() {
		Map<String, String> map = new HashMap<>();
		map.put("name", "wjz");
		map.put("address", "北京市石景山区");
		user.setStringMap(map);
		Object userVO = factory.transform(user);
		System.out.println(userVO);
	}
	
	@Test
	public void transformBeanWithBeanMap() {
		Map<String, Son> map = new HashMap<>();
		Son son = new Son();
		son.setId(7L);
		son.setName("bingjjfly");
		son.setStature(77.618);
		son.setBirthday(new Date());
		map.put("son", son);
		user.setSonMap(map);
		Object userVO = factory.transform(user);
		System.out.println(userVO);
	}
	
	@Test
	public void transformBeanWithBeanListMap() {
		Map<String, List<User>> map = new HashMap<>();
		List<User> list = new ArrayList<>();
		User user2 = new User();
		user2.setId(3);
		user2.setName("ping");
		user2.setAddTime(new Date());
		user2.setAsset(7797.86);
		list.add(user);
		list.add(user2);
		map.put("listMap", list);
		Object userVO = factory.transform(map);
		System.out.println(userVO);
	}
	
	@Test
	public void transformUnconventional() {
		user.setMonth(Month.JULY);
		Object userVO = factory.transform(user);
		System.out.println(userVO);
	}
	
	@Test
	public void transformBeanWithCrypto() {
		Object userVO = factory.transform(user);
		System.out.println(userVO);
		String id = ((UserVO) userVO).getId();
		System.out.println(CryptoPropertyHandler.decrypt(id));
	}
}
