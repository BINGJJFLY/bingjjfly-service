package com.wjz.service.manager;

import java.io.Serializable;
import java.util.List;

/**
 * <b>基础查询接口</b>
 * 
 * @author iss002
 * @param <T>
 */
public interface SelectService<T> extends SelectByPageInfoService<T> {

	/**
	 * 根据主键查询
	 * @param primaryKey
	 * @return
	 */
	T selectByPrimaryKey(Serializable primaryKey);

	/**
	 * 根据动态查询条件查询集合
	 * <p>
	 * 此方法需要复写，由具体的业务接口实现类具体实现
	 * @param t
	 * @return
	 */
	List<T> selectList(T t);
	
	/**
	 * 根据DO属性信息查询
	 * <p>
	 * 一个属性名对应一个属性值如：{"name" : "wjz"}
	 * @param property 属性名
	 * @param value 属性值
	 * @return
	 */
	List<T> selectByProperty(String property, Object value);
	
	/**
	 * 根据DO属性信息集合查询
	 * <p>
	 * 一个属性名对应一个属性值如：{"name" : "wjz", "age" : "18"}
	 * @param properties
	 * @param values
	 * @return
	 */
	List<T> selectByProperty(String[] properties, Object[] values);
	
}
