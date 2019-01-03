package com.wjz.service.vo.handler;

import com.wjz.service.exception.UnAssignableException;
import com.wjz.service.vo.magician.DO2VOMagician;

/**
 * <b>DO/VO对象转换回调接口</b>
 * 
 * @author iss002
 *
 */
public interface Converter {

	Object convert(DO2VOMagician magician, Object target) throws UnAssignableException;

}
