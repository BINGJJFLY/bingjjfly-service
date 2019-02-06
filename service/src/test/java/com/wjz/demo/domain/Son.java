package com.wjz.demo.domain;

import java.io.Serializable;
import java.util.Date;

import com.wjz.demo.vo.SonVO;
import com.wjz.service.anno.ViewObject;
import com.wjz.service.anno.ViewProperty;
import com.wjz.service.pojo.BaseBean;

@ViewObject(SonVO.class)
public class Son extends BaseBean<Son> implements Serializable {

	private static final long serialVersionUID = -6123663317521994545L;

	@ViewProperty(crypto = true)
	private Long id;

	private String name;

	@ViewProperty(pattern = "0.00")
	private Double stature;

	private Date birthday;

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

	public Double getStature() {
		return stature;
	}

	public void setStature(Double stature) {
		this.stature = stature;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
