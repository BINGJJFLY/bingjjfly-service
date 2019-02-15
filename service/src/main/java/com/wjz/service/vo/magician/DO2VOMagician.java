package com.wjz.service.vo.magician;

import com.wjz.service.vo.handler.PropertiesHandlerRegistry;

/**
 * <b>Domain对象转换View对象转换器 </b>
 * 
 * @author iss002
 *
 */
public interface DO2VOMagician {

	/**
	 * Domain对象转换View对象
	 * 
	 * @param domain
	 * @return
	 */
	Object do2vo(Object domain);
	
	/**
	 * Domain对象转换为指定类型的View对象
	 * 
	 * @param domain
	 * @param viewType
	 * @return
	 */
	Object do2vo(Object domain, Class<?> viewType);
	
	/**
	 * 设置属性处理器注册机
	 * 
	 * @param registy
	 */
	void setRegisty(PropertiesHandlerRegistry registy);

}
