package org.pretend.web.controller.task;

import org.pretend.monitor.api.abs.AbstractDefaultTaskMonitor;

public class PrintTask extends AbstractDefaultTaskMonitor implements Runnable {

	@Override
	public String getTaskDescription() {
		
		return "测试任务";
	}

	@Override
	public String getTaskId() {
		
		return "1";
	}

	@Override
	public Object getExceptionMsg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
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
