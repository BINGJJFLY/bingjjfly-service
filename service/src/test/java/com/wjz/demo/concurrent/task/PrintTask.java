package com.wjz.demo.concurrent.task;

/**
 * 具体的任务，执行相应的业务
 *
 * @author iss002
 *
 */
public class PrintTask implements Task {

	@Override
	public void doSomeThing() {
		System.out.println("当前线程[" + Thread.currentThread().getName() + "]正在打印");
	}

}
