package com.wjz.demo.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Integer> {

	private static final long serialVersionUID = -1184152092358129401L;
	
	private static final int THRESHOLD = 2;
	private int start;
	private int end;
	
	public CountTask(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		boolean canCompute = ( end - start ) <= THRESHOLD;
		if (canCompute) {
			for (int i = start; i <= end; i++) {
				sum += i;
			}
		} else {
			int middle = ( start + end ) / 2;
			CountTask task_1 = new CountTask(start, middle);
			CountTask task_2 = new CountTask(middle + 1, end);
//			invokeAll(task_1, task_2);
			task_1.fork();
			task_2.fork();
			int r1 = task_1.join();
			int r2 = task_2.join();
			sum = r1 + r2;
		}
		return sum;
	}

	public static void main(String[] args) {
		ForkJoinPool fjp = new ForkJoinPool();
		CountTask task = new CountTask(1, 4);
		ForkJoinTask<Integer> fj = fjp.submit(task);
		if (fj.isCompletedAbnormally()) {
			fj.getException().printStackTrace();
		}
		try {
			System.out.println(fj.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
