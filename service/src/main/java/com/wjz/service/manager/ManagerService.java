package com.wjz.service.manager;

/**
 * <b>基础接口定义</b>
 * <p>
 * 操作数据库的基础行为
 * <p>
 * 断言校验行为
 * @author iss002
 * @param <T>
 */
public interface ManagerService<T> extends 
	InsertService<T>, 
	DeleteService<T>, 
	UpdateService<T>, 
	SelectService<T>, 
	ProcedureService {

}