package com.wjz.service.concurrent.task;

public class EmailSendingTask implements Runnable {

	@Override
	public void run() {
		sendEmail();
	}

	private void sendEmail() {

	}
}
