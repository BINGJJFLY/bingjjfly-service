package com.wjz.demo.vo;

import java.io.Serializable;
import java.time.Month;
import java.util.List;
import java.util.Map;

import com.wjz.service.pojo.BaseBean;

public class UserVO extends BaseBean<UserVO> implements Serializable {

	private static final long serialVersionUID = -4694412964940248139L;

	private String id;

	private String personName;

	private String addTime;

	private String asset;
	
	private List<String> list;
	
	private List<SonVO> sons;
	
	private Map<String, String> stringMap;
	
	private Map<String, SonVO> sonMap;
	
	private Month month;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public List<SonVO> getSons() {
		return sons;
	}

	public void setSons(List<SonVO> sons) {
		this.sons = sons;
	}

	public Map<String, String> getStringMap() {
		return stringMap;
	}

	public void setStringMap(Map<String, String> stringMap) {
		this.stringMap = stringMap;
	}

	public Map<String, SonVO> getSonMap() {
		return sonMap;
	}

	public void setSonMap(Map<String, SonVO> sonMap) {
		this.sonMap = sonMap;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

}
