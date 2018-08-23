package com.wjz.demo;

import java.util.Date;
import java.util.List;

import com.wjz.service.pojo.BaseBean;

public class Person extends BaseBean<Person> {

	private static final long serialVersionUID = -8842122460259361832L;

	private Integer id;

	private String name;

	private Date addTime;

	private Double asset;
	
	private boolean used;
	
	private List<String> list;

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
