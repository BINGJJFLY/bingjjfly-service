package com.wjz.demo.vo;

import com.wjz.service.pojo.BaseBean;

public class AddressVO extends BaseBean<AddressVO> {

	private static final long serialVersionUID = -9036970823992722469L;

	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
