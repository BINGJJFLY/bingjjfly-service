package com.wjz.service.vo.handler;

import com.wjz.service.exception.UnAssignableException;

public interface Transformer {

	Object transforming(Object target) throws UnAssignableException;
	
}
