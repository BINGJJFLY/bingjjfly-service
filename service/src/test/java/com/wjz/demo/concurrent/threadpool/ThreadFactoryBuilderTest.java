package com.wjz.demo.concurrent.threadpool;

import java.util.concurrent.ThreadFactory;

import org.junit.Test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class ThreadFactoryBuilderTest {

	@Test
	public void build() {
		ThreadFactoryBuilder builder = new ThreadFactoryBuilder();
		ThreadFactory tf = builder.setNameFormat("线程名称-%s").build();
		Thread t = tf.newThread(() -> {
			
		});
		System.out.println(t.getName());
	}
}
