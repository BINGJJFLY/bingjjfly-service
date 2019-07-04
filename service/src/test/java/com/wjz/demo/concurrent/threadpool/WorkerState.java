package com.wjz.demo.concurrent.threadpool;

import java.util.concurrent.atomic.AtomicInteger;

public class WorkerState {

	// ( 32 - 3 ) = 29，字节长度
	private static final int COUNT_BITS = Integer.SIZE - 3;
	// ( 2^29 - 1 ) = 536870911，有界队列的最大容量值
	private static final int CAPACITY = (1 << COUNT_BITS) - 1;

	// runState is stored in the high-order bits
	// 运行状态存储在高位
	// ( -1 * 2^29 ) = -536870912
	private static final int RUNNING = -1 << COUNT_BITS;
	// ( 0 * 2^29 ) = 0
	private static final int SHUTDOWN = 0 << COUNT_BITS;
	// ( 1 * 2^29 ) = 536870912
	private static final int STOP = 1 << COUNT_BITS;
	// ( 2 * 2^29 ) = 1073741824
	private static final int TIDYING = 2 << COUNT_BITS;
	// ( 3 * 2^29 ) = 1610612736
	private static final int TERMINATED = 3 << COUNT_BITS;
	// -536870912
	private static final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));

	// Packing and unpacking ctl
	//
	private static int runStateOf(int c) {
		return c & ~CAPACITY;
	}

	private static int workerCountOf(int c) {
		return c & CAPACITY;
	}

	private static int ctlOf(int rs, int wc) {
		return rs | wc;
	}

	/*
	 * Bit field accessors that don't require unpacking ctl. These depend on the bit
	 * layout and on workerCount being never negative.
	 */

	private static boolean runStateLessThan(int c, int s) {
		return c < s;
	}

	private static boolean runStateAtLeast(int c, int s) {
		return c >= s;
	}

	private static boolean isRunning(int c) {
		return c < SHUTDOWN;
	}

	public static void main(String[] args) {
		// 11111111111111111111111111110000 = -1 * 16 ( 0001 0000 ) = -16
		System.out.println(Integer.toBinaryString(~15));
		// 按位取反
		// +536870911 = 0001 1111 1111 1111 1111 1111 1111 1111
		// -536870912 = 1110 0000 0000 0000 0000 0000 0000 0000
		System.out.println(~CAPACITY);
		// 1110 0000 0000 0000 0000 0000 0000 0000
		System.out.println(Integer.toBinaryString(~CAPACITY));
		// 1110 0000 0000 0000 0000 0000 0000 0000
		// 0000 0000 0000 0000 0000 0000 0000 0000
		// 同0取0，否则取1
		// 1110 0000 0000 0000 0000 0000 0000 0000
		System.out.println(ctl.get());

		// 1110 0000 0000 0000 0000 0000 0000 0000
		// 1110 0000 0000 0000 0000 0000 0000 0000
		// 同1取1，否则为0
		// 1110 0000 0000 0000 0000 0000 0000 0000
		System.out.println(runStateOf(ctl.get()));
		// 1110 0000 0000 0000 0000 0000 0000 0000
		// 0001 1111 1111 1111 1111 1111 1111 1111
		// 同1取1，否则为0
		// 0000 0000 0000 0000 0000 0000 0000 0000
		System.out.println(workerCountOf(ctl.get()));

		// 1110 0000 0000 0000 0000 0000 0000 0001
		// 1110 0000 0000 0000 0000 0000 0000 0000
		// 同1取1，否则为0
		// 1110 0000 0000 0000 0000 0000 0000 0000
		// 低位数值改变不会影响总体的值
		System.out.println(runStateOf(ctl.incrementAndGet()));
		
		// 1110 0000 0000 0000 0000 0000 0000 0001
		// 0001 1111 1111 1111 1111 1111 1111 1111
		// 同1取1，否则为0
		// 0000 0000 0000 0000 0000 0000 0000 0001
		// 低位数值改变会影响总体的值
		System.out.println(workerCountOf(ctl.get()));
		
		// 0
		// 1
		// 同0取0，否则为1
		System.out.println(ctlOf(SHUTDOWN, workerCountOf(ctl.get())));
		
		// 0010 0000 0000 0000 0000 0000 0000 0000
		// 0000 0000 0000 0000 0000 0000 0000 0001
		// 0010 0000 0000 0000 0000 0000 0000 0001
		System.out.println(ctlOf(STOP, workerCountOf(ctl.get())));
	}
}
