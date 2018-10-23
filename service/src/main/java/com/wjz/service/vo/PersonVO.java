package com.wjz.service.vo;

import java.io.Serializable;

public class PersonVO implements Serializable {

	private static final long serialVersionUID = 6032857658992617572L;
	
	private String id;
	
	private String personName;
	
	private String addTime;
	
	private String asset;

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

	@Override
	public String toString() {
		return "PersonVO [id=" + id + ", personName=" + personName + ", addTime=" + addTime + ", asset=" + asset + "]";
	}

}
