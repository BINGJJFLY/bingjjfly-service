package com.wjz.demo.concurrent.threadpool.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.junit.Assert;
import org.junit.Test;

public class FutureTaskTest {
	
	private final ConcurrentMap<Object, Future<String>> futures = new ConcurrentHashMap<>();
	
	public String execute(final String taskName) {
		while (true) {
			Future<String> future = futures.get(taskName);
			if (future == null) {
				Callable<String> task = new Callable<String>() {
					@Override
					public String call() throws Exception {
						return taskName;
					}
				};
				FutureTask<String> futureTask = new FutureTask<>(task);
				future = futures.putIfAbsent(taskName, futureTask);
				if (future == null) {
					future = futureTask;
					futureTask.run();
				}
			}
			try {
				future.get();
			} catch (Exception e) {
				futures.remove(taskName, future);
			}
		}
	}

	@Test
	public void putIfAbsent() {
		ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
		String result = map.putIfAbsent("hello", "world");
		Assert.assertEquals(null, result);
		Assert.assertEquals("world", map.get("hello"));
		result = map.putIfAbsent("hello", "bing");
		Assert.assertEquals("world", result);
		Assert.assertEquals("world", map.get("hello"));
	}
}
