package com.wjz.demo.concurrent.threadpool.scheduled;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ScheduleAtFixedRateTest {
	
	@Test
	public void scheduleAtFixedRate() {
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
		// 延迟3秒，每隔1秒周期性执行
		ScheduledFuture<?> future = executor.scheduleAtFixedRate(new Task(), 3000, 1000, TimeUnit.MILLISECONDS);
		try {
			future.get();
		} catch (Exception e) {
			//
		}
	}
	
	/*
	 public ScheduledFuture<?> scheduleAtFixedRate(Runnable command,
                                                  long initialDelay,
                                                  long period,
                                                  TimeUnit unit) {
        if (command == null || unit == null)
            throw new NullPointerException();
        if (period <= 0)
            throw new IllegalArgumentException();
        ScheduledFutureTask<Void> sft =
            new ScheduledFutureTask<Void>(command,
                                          null,
                                          triggerTime(initialDelay, unit),
                                          unit.toNanos(period));
        RunnableScheduledFuture<Void> t = decorateTask(command, sft);
        $ 周期性执行的任务，添加到队列中再取出再添加，周而复始 $
        sft.outerTask = t;
        delayedExecute(t);
        return t;
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
