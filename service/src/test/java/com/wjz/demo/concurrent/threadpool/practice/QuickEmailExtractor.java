package com.wjz.demo.concurrent.threadpool.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class QuickEmailExtractor implements EmailExtractor {
	
	private final ThreadPoolExecutor executor;
	private final LinkedBlockingQueue<String> queue;

	private int corePoolSize = Runtime.getRuntime().availableProcessors() * 2;
	private int maximumPoolSize = corePoolSize;
	private long keepAliveTime = 1000;
	
	public QuickEmailExtractor() {
		executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100));
		queue = new LinkedBlockingQueue<>();
	}
	
	@Override
	public void extract() {
		// 生产者开始生产
		new ExtractorThread().start();
		// 消费者开始消费
		insertIntoWiki();
	}

	private void insertIntoWiki() {
		while (true) {
			try {
				String email = queue.poll(2, TimeUnit.SECONDS);
				if (email == null) {
					break;
				}
				executor.submit(new InsertIntoWikiTask(email));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void extractEmail() {
		// 查出所有的邮件
		List<String> emails = new ArrayList<String>();
		if (emails != null) {
			for (String email : emails) {
				queue.offer(email);
			}
		}
	}
	
	class ExtractorThread extends Thread {
		@Override
		public void run() {
			extractEmail();
		}
	}
	
	class InsertIntoWikiTask implements Runnable {
		String email;
		public InsertIntoWikiTask(String email) {
			this.email = email;
		}
		@Override
		public void run() {
			// insert(email);
		}
	}
}
