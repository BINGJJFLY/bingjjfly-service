package com.wjz.service.concurrent.executor;

import java.util.concurrent.Executor;

/**
 * <b>任务执行器</b>
 *
 * @author iss002
 *
 */
public interface TaskExecutor extends Executor {

	@Override
	void execute(Runnable task);
}
