package com.wjz.demo.concurrent;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UnsafeHashMap {

	public static void main(String[] args) throws InterruptedException {
		final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>(2);
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							map.put(UUID.randomUUID().toString(), "");
						}
					}, "ftf" + i).start();
				}
			}
		}, "ftf");
		t.start();
		t.join();
	}

}
