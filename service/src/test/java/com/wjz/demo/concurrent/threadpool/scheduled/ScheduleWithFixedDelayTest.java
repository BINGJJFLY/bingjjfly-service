package com.wjz.demo.concurrent.threadpool.scheduled;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 创建一个Runnable周期任务，在initialDelay时间开始执行，以一次任务结束的时间为起点，每隔delay时间再执行一次
 *
 * @author iss002
 *
 */
public class ScheduleWithFixedDelayTest {
	
	@Test
	public void scheduleWithFixedDelay() {
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
		ScheduledFuture<?> future = executor.scheduleWithFixedDelay(new Task(), 3000, 1000, TimeUnit.MILLISECONDS);
		try {
			future.get();
		} catch (Exception e) {
			//
		}
	}
	
	/*
	 ScheduledFutureTask<Void> sft =
            new ScheduledFutureTask<Void>(command,
                                          null,
                                          triggerTime(initialDelay, unit),
                                          $ period小于0 $
                                          unit.toNanos(-delay));
	 */
	
	/*
	 private void setNextRunTime() {
	 	$ 每隔period时间再执行一次时period大于0 $
	 	$ 以一次任务结束的时间为起点，每隔delay时间再执行一次时period小于0 $
        long p = period;
        if (p > 0)
            time += p;
        else
            time = triggerTime(-p);
     }
	 */

	class Task implements Runnable {

		@Override
		public void run() {
			System.out.println("Working");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
