package com.wjz.demo.vo.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.wjz.demo.domain.Address;
import com.wjz.demo.domain.IdCard;
import com.wjz.demo.domain.SingleUser;
import com.wjz.demo.domain.Son;
import com.wjz.demo.vo.SingleUserVO;
import com.wjz.service.vo.handler.PropertiesHandlerRegistry;
import com.wjz.service.vo.magician.SingleDO2VOMagician;

public class SingleDO2VOMagicianTest {

	private SingleUser user;
	
	private SingleDO2VOMagician magician = new SingleDO2VOMagician();

	@Before
	public void init() {
		user = new SingleUser();
		user.setId(8L);
		user.setName("iss002");
		user.setAddTime(new Date());
		user.setAsset(887426823447.86);
		user.setAge(18);
		user.setSalary(new BigDecimal(14732.98));
		
		PropertiesHandlerRegistry registry = new PropertiesHandlerRegistry();
		registry.setPackageName("com/wjz/service/vo/extension");
		magician.setRegisty(registry);
	}

	@Test
	public void single() {
		SingleUserVO userVO = (SingleUserVO) magician.do2vo(user);
		System.out.println(userVO);
		System.out.println(userVO.getId());
		System.out.println(userVO.getPersonName());
		System.out.println(userVO.getAddTime());
		System.out.println(userVO.getAsset());
		System.out.println(userVO.getAge());
	}

	@Test
	public void list() {
		try {
			List<Son> sons = new ArrayList<>();
			Son son = new Son();
			son.setId(7L);
			son.setName("hello");
			sons.add(son);
			son = new Son();
			son.setId(10L);
			son.setName("world");
			son.setBirthday(new Date());
			son.setStature(743.32);
			sons.add(son);
			user.setSons(sons);
			SingleUserVO userVO = (SingleUserVO) magician.do2vo(user);
			System.out.println(userVO);
			System.out.println(userVO.getSons());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void bean() {
		try {
			Son son = new Son();
			son.setId(10L);
			son.setName("world");
			son.setBirthday(new Date());
			son.setStature(743.32);

			IdCard idCard = new IdCard();
			idCard.setCode("X3794267193");

			Address address = new Address();
			address.setAddress("abc");
			user.setSon(son);
			user.setIdCard(idCard);
			user.setSonNo(son);
			user.setAddress(address);
			user.setS((short) 7797);

			SingleUserVO userVO = (SingleUserVO) magician.do2vo(user);
			System.out.println(userVO);
			System.out.println(userVO.getSon());
			System.out.println(userVO.getIdCard());
			System.out.println(userVO.getSonNo());
			System.out.println(userVO.getAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void map() {
		Map<String, String> stringMap = new HashMap<>();
		stringMap.put("1", "hello");
		stringMap.put("2", "world");
		stringMap.put("3", "!");
		Map<String, Address> addresses = new HashMap<>();
		Address address = new Address();
		address.setAddress("abc");
		addresses.put("addresses", address);

		user.setStringMap(stringMap);
		user.setAddresses(addresses);

		SingleUserVO userVO = (SingleUserVO) magician.do2vo(user);
		System.out.println(userVO);
		System.out.println(userVO.getStringMap());
		System.out.println(userVO.getAddresses());
	}
	
	@Test
	public void expandability() {
		Set<Son> sonSet = new HashSet<>();
		Son son = new Son();
		son.setId(10L);
		son.setName("world");
		son.setBirthday(new Date());
		son.setStature(7797.18);
		sonSet.add(son);
		user.setSonSet(sonSet);
		
		SingleUserVO userVO = (SingleUserVO) magician.do2vo(user);
		System.out.println(userVO);
		System.out.println(userVO.getSonSet());
	}

}
