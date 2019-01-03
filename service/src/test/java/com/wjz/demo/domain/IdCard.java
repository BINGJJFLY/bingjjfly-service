package com.wjz.demo.domain;

import com.wjz.demo.vo.IdCardVO;
import com.wjz.service.annotation.ViewObject;
import com.wjz.service.pojo.BaseBean;

@ViewObject(IdCardVO.class)
public class IdCard extends BaseBean<IdCard> {

	private static final long serialVersionUID = 7895954213209946264L;

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
