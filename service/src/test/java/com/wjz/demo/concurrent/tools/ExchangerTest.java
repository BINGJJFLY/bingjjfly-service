package com.wjz.demo.concurrent.tools;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程间交换数据，可用于校对数据
 *
 * @author iss002
 *
 */
public class ExchangerTest {

	private static Exchanger<String> exgr = new Exchanger<>();
	private static ExecutorService pool = Executors.newFixedThreadPool(2);

	public static void main(String[] args) {
		pool.execute(new Runnable() {
			@Override
			public void run() {
				String x = "银行流水A";
				try {
					exgr.exchange(x);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		pool.execute(new Runnable() {
			@Override
			public void run() {
				String x = "银行流水B";
				try {
					String a = exgr.exchange(x);
					System.out.println("是否一致：" + a.equals(x) + "，A录入[" + a + "]");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		pool.shutdown();
	}
}
