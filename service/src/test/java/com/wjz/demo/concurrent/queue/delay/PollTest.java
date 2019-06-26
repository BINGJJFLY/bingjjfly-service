package com.wjz.demo.concurrent.queue.delay;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

public class PollTest {

	@Test
	public void poll() {
		DelayQueue<Delayer> queue = new DelayQueue<>();
		Delayer d1 = new Delayer();
		d1.setTime(5L);
		queue.offer(d1);
		Assert.assertEquals(null, queue.poll());
		d1.setTime(0L);
		Assert.assertEquals(d1, queue.poll());
	}

	@Test
	public void test() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				long mills = 5000;
				DelayQueue<Tester> queue = new DelayQueue<>();
				Tester d1 = new Tester();
				d1.setTime(System.currentTimeMillis() + mills);
				queue.offer(d1);
				Tester result;
				while ((result = queue.poll()) == null) {
				}
				System.out.println(result);
			}
		});
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	class Delayer implements Delayed {

		private Long time;

		public Long getTime() {
			return time;
		}

		public void setTime(Long time) {
			this.time = time;
		}

		@Override
		public int compareTo(Delayed o) {
			return this.time.compareTo(((Delayer) o).getTime());
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return unit.toNanos(time);
		}

	}

	class Tester implements Delayed {

		private Long time;

		public Long getTime() {
			return time;
		}

		public void setTime(Long time) {
			this.time = time;
		}

		@Override
		public int compareTo(Delayed o) {
			return this.time.compareTo(((Delayer) o).getTime());
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return unit.toNanos(time - System.currentTimeMillis());
		}

	}
}
