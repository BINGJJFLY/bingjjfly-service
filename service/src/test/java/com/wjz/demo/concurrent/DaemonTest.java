package com.wjz.demo.concurrent;

/**
 * 守护线程和用户线程的区别
 * User Thread线程和Daemon Thread唯一的区别之处就在虚拟机的离开
 * 随着JVM退出，守护线程不管是否运行结束都要伴随着JVM的退出而退出
 * 
 * @author iss002
 *
 */
public class DaemonTest {
	
	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("t over");
			}
		});
		t.setDaemon(true);
		t.start();
		System.out.println("Main over");
	}

}
