package com.wjz.demo.concurrent.executor;

import java.util.LinkedList;

import com.wjz.demo.concurrent.task.Task;

/**
 * 任务执行者（消费者）
 *
 * @author iss002
 *
 */
public class TaskExecutor implements Runnable {

	private LinkedList<Task> tasks;
	private Thread thread;
	private boolean running = true;

	public TaskExecutor(LinkedList<Task> tasks) {
		this.tasks = tasks;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	@Override
	public void run() {
		while (running) {
			Task task = null;
			synchronized (tasks) {
				while (tasks.isEmpty()) {
					try {
						tasks.wait();
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						return;
					}
				}
				task = tasks.removeFirst();
			}
			if (task != null) {
				task.doSomeThing();
			}
		}
	}

	public void execute() {
		if (thread != null) {
			thread.start();
		}
	}

	public void shutdown() {
		running = false;
		thread.interrupt();
	}
}
