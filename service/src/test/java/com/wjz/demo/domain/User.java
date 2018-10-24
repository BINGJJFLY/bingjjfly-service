package com.wjz.demo.domain;

import java.io.Serializable;
import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wjz.demo.vo.UserVO;
import com.wjz.service.annotation.ViewObject;
import com.wjz.service.annotation.ViewProperty;
import com.wjz.service.pojo.BaseBean;

@ViewObject(UserVO.class)
public class User extends BaseBean<User> implements Serializable {

	private static final long serialVersionUID = 5683379393982900285L;

	private Integer id;

	@ViewProperty("personName")
	private String name;

	@ViewProperty(pattern = "yyyy-MM-dd")
	private Date addTime;

	@ViewProperty(pattern = "0.00")
	private Double asset;

	private List<String> list;
	
	private List<Son> sons;
	
	private Map<String, String> stringMap;
	
	private Map<String, Son> sonMap;
	
	private Month month;

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public Map<String, Son> getSonMap() {
		return sonMap;
	}

	public void setSonMap(Map<String, Son> sonMap) {
		this.sonMap = sonMap;
	}

	public Map<String, String> getStringMap() {
		return stringMap;
	}

	public void setStringMap(Map<String, String> stringMap) {
		this.stringMap = stringMap;
	}

	public List<Son> getSons() {
		return sons;
	}

	public void setSons(List<Son> sons) {
		this.sons = sons;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
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

}
