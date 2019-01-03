package com.wjz.demo.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wjz.demo.domain.Address;
import com.wjz.demo.domain.Son;
import com.wjz.service.pojo.BaseBean;

public class SingleUserVO extends BaseBean<SingleUserVO> implements Serializable {

	private static final long serialVersionUID = 267504549196263694L;

	private String id;
	
	private String name;
	
	private String personName;
	
	private String addTime;
	
	private String asset;
	
	private String age;
	
	private List<SonVO> sons;
	
	private Address address;
	
	private Map<String, AddressVO> addresses;
	
	private SonVO son;
	
	private short s;
	
	private IdCardVO idCard;
	
	private Map<String, String> stringMap;
	
	private Son sonNo;
	
	private Set<SonVO> sonSet;
	
	private String salary;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public List<SonVO> getSons() {
		return sons;
	}

	public void setSons(List<SonVO> sons) {
		this.sons = sons;
	}

	public SonVO getSon() {
		return son;
	}

	public void setSon(SonVO son) {
		this.son = son;
	}

	public IdCardVO getIdCard() {
		return idCard;
	}

	public void setIdCard(IdCardVO idCard) {
		this.idCard = idCard;
	}

	public Son getSonNo() {
		return sonNo;
	}

	public void setSonNo(Son sonNo) {
		this.sonNo = sonNo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public short getS() {
		return s;
	}

	public void setS(short s) {
		this.s = s;
	}

	public Map<String, String> getStringMap() {
		return stringMap;
	}

	public void setStringMap(Map<String, String> stringMap) {
		this.stringMap = stringMap;
	}

	public Map<String, AddressVO> getAddresses() {
		return addresses;
	}

	public void setAddresses(Map<String, AddressVO> addresses) {
		this.addresses = addresses;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public Set<SonVO> getSonSet() {
		return sonSet;
	}

	public void setSonSet(Set<SonVO> sonSet) {
		this.sonSet = sonSet;
	}

}
