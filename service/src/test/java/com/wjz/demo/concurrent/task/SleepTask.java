package com.wjz.demo.concurrent.task;

public class SleepTask implements Task {

	@Override
	public void doSomeThing() {
		try {
			Thread.sleep(500);
			System.out.println("当前线程[" + Thread.currentThread().getName() + "]正在睡眠");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
