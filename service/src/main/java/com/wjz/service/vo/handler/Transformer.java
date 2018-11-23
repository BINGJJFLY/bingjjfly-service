package com.wjz.service.vo.handler;

import com.wjz.service.exception.UnAssignableException;

/**
 * <b>DO\VO对象转换回调接口</b>
 * <p>
 * 属性为 {@code Collection} 或 {@code Map} 类型时进行回调处理
 * </p>
 * 
 * @author iss002
 *
 */
public interface Transformer {

	Object transforming(Object target) throws UnAssignableException;

}
