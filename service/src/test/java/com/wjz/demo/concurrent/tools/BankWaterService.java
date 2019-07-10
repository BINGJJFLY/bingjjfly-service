package com.wjz.demo.concurrent.tools;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 银行流水处理服务类（4个Sheet页）
 * 该功能也可以使用CountDownLatch完成
 *
 * @author iss002
 *
 */
public class BankWaterService implements Runnable {
	
	/**
	 * 创建4个屏障，处理完之后执行当前类的run方法
	 */
	private CyclicBarrier c = new CyclicBarrier(4, this);
	
	/**
	 * 假设有四个Sheet页，开启四个线程
	 */
	private ExecutorService executor = Executors.newFixedThreadPool(4);
	
	/**
	 * 保存每个sheet计算出的银流结果
	 */
	private ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>(4, 1);

	public void count() {
		for (int i = 0; i < 4; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					// 模拟计算结果
					map.put(Thread.currentThread().getName(), 1);
					try {
						// 计算完后插入一个屏障
						c.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		executor.shutdown();
	}
	
	@Override
	public void run() {
		int result = 0;
		if (!map.isEmpty()) {
			for (Map.Entry<String, Integer> entry : map.entrySet()) {
				result += entry.getValue();
			}
			System.out.println("Result：" + result);
		}
	}

	public static void main(String[] args) {
		BankWaterService service = new BankWaterService();
		service.count();
	}
	
}
