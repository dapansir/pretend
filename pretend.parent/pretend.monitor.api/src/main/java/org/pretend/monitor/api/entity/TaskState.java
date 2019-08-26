package org.pretend.monitor.api.entity;

import java.util.Date;

public class TaskState {
	
	private String taskMoudle;
	
	private Date startDate;
	
	private Date stopDate;
	
	private int taskState;
	
	private long dataConut;
	
	private long dealedDataCount;
	
	private String taskClassName;
	
	private String threadName;
	
	private long hasExcutedTime;
	
	private long unDealedDataCount;
	
	private long averageTime;
	
	private long estimatedTime;
	
	private Date estimatedEndDate;
	
	private String taskId;
	
	private  boolean running;
	
	private String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTaskMoudle() {
		return taskMoudle;
	}

	public void setTaskMoudle(String taskMoudle) {
		this.taskMoudle = taskMoudle;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public int getTaskState() {
		return taskState;
	}

	public void setTaskState(int taskState) {
		this.taskState = taskState;
	}

	public long getDataConut() {
		return dataConut;
	}

	public void setDataConut(long dataConut) {
		this.dataConut = dataConut;
	}

	public long getDealedDataCount() {
		return dealedDataCount;
	}

	public void setDealedDataCount(long dealedDataCount) {
		this.dealedDataCount = dealedDataCount;
	}

	public String getTaskClassName() {
		return taskClassName;
	}

	public void setTaskClassName(String taskClassName) {
		this.taskClassName = taskClassName;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public long getHasExcutedTime() {
		return hasExcutedTime;
	}

	public void setHasExcutedTime(long hasExcutedTime) {
		this.hasExcutedTime = hasExcutedTime;
	}

	public long getUnDealedDataCount() {
		return unDealedDataCount;
	}

	public void setUnDealedDataCount(long unDealedDataCount) {
		this.unDealedDataCount = unDealedDataCount;
	}

	public long getAverageTime() {
		return averageTime;
	}

	public void setAverageTime(long averageTime) {
		this.averageTime = averageTime;
	}

	public long getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(long estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public Date getEstimatedEndDate() {
		return estimatedEndDate;
	}

	public void setEstimatedEndDate(Date estimatedEndDate) {
		this.estimatedEndDate = estimatedEndDate;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
	
}
