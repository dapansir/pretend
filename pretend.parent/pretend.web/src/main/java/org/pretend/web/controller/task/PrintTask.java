package org.pretend.web.controller.task;

import org.pretend.monitor.api.abs.AbstractDefaultTaskMonitor;

public class PrintTask extends AbstractDefaultTaskMonitor implements Runnable {

	public String getTaskDescription() {
		
		return "测试任务";
	}

	public String getTaskId() {
		
		return "1";
	}

	public Object getExceptionMsg() {
		// TODO 
		return null;
	}

	public void run() {
		while (true) {
			System.out.println("aaaaaa");
			try {
				Thread.sleep(60 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}

}
