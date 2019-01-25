package com.wjz.service.manager;

import com.wjz.service.manager.delete.batch.BatchDeleteService;
import com.wjz.service.manager.insert.batch.BatchInsertService;
import com.wjz.service.manager.procedure.ProcedureService;
import com.wjz.service.manager.select.page.SelectByPageInfoService;
import com.wjz.service.manager.update.batch.BatchUpdateService;

/**
 * <b>数据库基础操作接口</b>
 * 
 * @author iss002
 * @param <T>
 */
public interface ManagerService<T> extends 
		BatchInsertService<T>,
		BatchDeleteService<T>,
		BatchUpdateService<T>, 
		SelectByPageInfoService<T>, 
		ProcedureService<T> {

}
