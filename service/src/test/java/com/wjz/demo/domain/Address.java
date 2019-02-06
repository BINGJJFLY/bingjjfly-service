package com.wjz.demo.domain;

import com.wjz.demo.vo.AddressVO;
import com.wjz.service.anno.ViewObject;
import com.wjz.service.pojo.BaseBean;

@ViewObject(AddressVO.class)
public class Address extends BaseBean<Address> {

	private static final long serialVersionUID = 6685814183103488286L;

	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
