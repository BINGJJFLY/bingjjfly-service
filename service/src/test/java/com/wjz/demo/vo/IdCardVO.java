package com.wjz.demo.vo;

import com.wjz.service.pojo.BaseBean;

public class IdCardVO extends BaseBean<IdCardVO> {

	private static final long serialVersionUID = 4976459052056275609L;

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
