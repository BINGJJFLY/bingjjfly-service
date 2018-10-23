package com.wjz.demo.vo;

import java.io.Serializable;
import com.wjz.service.pojo.BaseBean;

public class SonVO extends BaseBean<SonVO> implements Serializable {

	private static final long serialVersionUID = 1508802427802361900L;

	private String id;

	private String name;

	private String stature;

	private String birthday;

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

	public String getStature() {
		return stature;
	}

	public void setStature(String stature) {
		this.stature = stature;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
