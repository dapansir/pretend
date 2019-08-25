package org.pretend.monitor.task;

public interface TaskHandler {

	boolean canStart();
	
	boolean suspend();
	
	boolean restart();
	
	boolean releaseData();
	
}
