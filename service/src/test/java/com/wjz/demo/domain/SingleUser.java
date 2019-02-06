package com.wjz.demo.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wjz.demo.vo.SingleUserVO;
import com.wjz.service.anno.ViewObject;
import com.wjz.service.anno.ViewProperty;

@ViewObject(SingleUserVO.class)
public class SingleUser implements Serializable {

	private static final long serialVersionUID = -168485125484778734L;

	private Long id;

	@ViewProperty(value = "personName")
	private String name;

	@ViewProperty(pattern = "yyyy-MM-dd")
	private Date addTime;

	@ViewProperty(pattern = "0.00")
	private Double asset;
	
	private Integer age;
	
	private List<Son> sons;
	
	private Son son;
	
	private IdCard idCard;
	
	private Map<String, String> stringMap;
	
	private short s;
	
	private Set<Son> sonSet;
	
	@ViewProperty(convertible=false)
	private Son sonNo;
	
	private BigDecimal salary;
	
	private Map<String, Address> addresses;
	
	@ViewProperty(working=false)
	private Address address;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Double getAsset() {
		return asset;
	}

	public void setAsset(Double asset) {
		this.asset = asset;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<Son> getSons() {
		return sons;
	}

	public void setSons(List<Son> sons) {
		this.sons = sons;
	}

	public Son getSon() {
		return son;
	}

	public void setSon(Son son) {
		this.son = son;
	}

	public IdCard getIdCard() {
		return idCard;
	}

	public void setIdCard(IdCard idCard) {
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

	public Map<String, Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Map<String, Address> addresses) {
		this.addresses = addresses;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Set<Son> getSonSet() {
		return sonSet;
	}

	public void setSonSet(Set<Son> sonSet) {
		this.sonSet = sonSet;
	}

}
