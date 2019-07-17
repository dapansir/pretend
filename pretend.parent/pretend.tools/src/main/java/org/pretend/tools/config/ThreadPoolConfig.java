package org.pretend.tools.config;

import java.util.concurrent.TimeUnit;

public class ThreadPoolConfig extends AbstractConfig{

	private String threadName;
	
	private int corePoolSize;
	
	private int maxSize;
	
	private int queue_Capacity;
	
	private long keepAliveTime;
	
	private TimeUnit unit;
	
	private String threadFactory;
	
	private String handler;

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public int getCorePoolSize() {
		return corePoolSize;
	}

	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public int getQueue_Capacity() {
		return queue_Capacity;
	}

	public void setQueue_Capacity(int queue_Capacity) {
		this.queue_Capacity = queue_Capacity;
	}

	public long getKeepAliveTime() {
		return keepAliveTime;
	}

	public void setKeepAliveTime(long keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}

	public TimeUnit getUnit() {
		return unit;
	}

	public void setUnit(TimeUnit unit) {
		this.unit = unit;
	}

	public String getThreadFactory() {
		return threadFactory;
	}

	public void setThreadFactory(String threadFactory) {
		this.threadFactory = threadFactory;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

}
