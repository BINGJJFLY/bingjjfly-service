package com.wjz.demo.concurrent.threadpool.scheduled;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ScheduleTest {
	
	@Test
	public void scheduleRunnable() {
		//创建一个Runnable定时任务，并在延迟指定时间后执行
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
		ScheduledFuture<?> future = executor.schedule(new TaskR(), 3000, TimeUnit.MILLISECONDS);
		try {
			// 等待任务完成
			future.get();
		} catch (Exception e) {
			//
		}
	}
	
	/*
	 public ScheduledFuture<?> schedule(Runnable command,
                                       long delay,
                                       TimeUnit unit) {
        if (command == null || unit == null)
            throw new NullPointerException();
        $ 包装任务为ScheduledFutureTask $
        RunnableScheduledFuture<?> t = decorateTask(command,
            $ 包装任务且计算出执行任务的时间 $
            new ScheduledFutureTask<Void>(command, null,
                                          triggerTime(delay, unit)));
        $ 准备执行任务 $
        delayedExecute(t);
        return t;
     }
     
     $ 当前时间加上延迟时间即任务执行的时间 $
     long triggerTime(long delay) {
        return now() +
            ((delay < (Long.MAX_VALUE >> 1)) ? delay : overflowFree(delay));
     }
	 */
	
	/*
	 private void delayedExecute(RunnableScheduledFuture<?> task) {
        if (isShutdown())
            reject(task);
        else {
        	$ 添加任务，获取任务时线程等待延迟时间时长 $
            super.getQueue().add(task);
            if (isShutdown() &&
                !canRunInCurrentRunState(task.isPeriodic()) &&
                remove(task))
                task.cancel(false);
            else
                ensurePrestart();
        }
     }
     
     void ensurePrestart() {
        int wc = workerCountOf(ctl.get());
        if (wc < corePoolSize)
        	$ 添加工作线程，任务是null，执行线程从队列中取任务 $
        	$ while (task != null || (task = getTask()) != null) $
            addWorker(null, true);
        else if (wc == 0)
            addWorker(null, false);
     }
	 */

	/*
	 * DelayedWorkQueue队列中获取任务
	 
	 public RunnableScheduledFuture<?> take() throws InterruptedException {
            final ReentrantLock lock = this.lock;
            lock.lockInterruptibly();
            try {
                for (;;) {
                    RunnableScheduledFuture<?> first = queue[0];
                    if (first == null)
                        available.await();
                    else {
                    	$ 获取任务的延迟时间 $
                        long delay = first.getDelay(NANOSECONDS);
                        $ 已经过了延迟时间，立即执行任务 $
                        if (delay <= 0)
                            return finishPoll(first);
                        first = null; // don't retain ref while waiting
                        if (leader != null)
                            available.await();
                        else {
                            Thread thisThread = Thread.currentThread();
                            leader = thisThread;
                            try {
                            	$ 等待延迟时间长度 $
                                available.awaitNanos(delay);
                            } finally {
                                if (leader == thisThread)
                                    leader = null;
                            }
                        }
                    }
                }
            } finally {
                if (leader == null && queue[0] != null)
                    available.signal();
                lock.unlock();
            }
        }
	 */
	
	@Test
	public void scheduleCallable() {
		//创建一个Runnable定时任务，并在延迟指定时间后执行
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
		ScheduledFuture<String> future = executor.schedule(new TaskC(), 3000, TimeUnit.MILLISECONDS);
		try {
			// 等待任务完成
			future.get();
		} catch (Exception e) {
			//
		}
	}
	
	class TaskR implements Runnable {

		@Override
		public void run() {
			System.out.println("Working");
		}
		
	}
	
	class TaskC implements Callable<String> {

		@Override
		public String call() throws Exception {
			System.out.println("Working");
			return null;
		}

	}
}
