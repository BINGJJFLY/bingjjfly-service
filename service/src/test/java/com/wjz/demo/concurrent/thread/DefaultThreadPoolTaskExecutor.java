package com.wjz.demo.concurrent.thread;

import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.wjz.demo.concurrent.executor.TaskExecutor;
import com.wjz.demo.concurrent.task.Task;

public class DefaultThreadPoolTaskExecutor {

	private int poolSize = 5;
	private LinkedList<Task> tasks = new LinkedList<>();
	private LinkedList<TaskExecutor> taskExecutors = new LinkedList<>();

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	public DefaultThreadPoolTaskExecutor() {
		init();
		start();
	}

	public void execute(Task task) {
		if (task != null) {
			synchronized (tasks) {
				tasks.add(task);
				tasks.notifyAll();
			}
		}
	}

	public void init() {
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		for (int i = 0; i < poolSize; i++) {
			TaskExecutor taskExecutor = new TaskExecutor(tasks);
			taskExecutors.add(taskExecutor);
			Thread t = threadFactory.newThread(taskExecutor);
			taskExecutor.setThread(t);
		}
	}

	public void start() {
		for (TaskExecutor taskExecutor : taskExecutors) {
			taskExecutor.execute();
		}
	}

}
