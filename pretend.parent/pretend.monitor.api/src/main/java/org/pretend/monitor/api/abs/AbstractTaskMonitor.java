package org.pretend.monitor.api.abs;


import java.util.Date;

import org.pretend.monitor.api.TaskMonitor;
import org.pretend.monitor.api.entity.TaskState;
import org.pretend.tools.util.NetUtil;

public abstract class AbstractTaskMonitor implements TaskMonitor {

	private String taskMoudle;
	
	private Date startDate;
	
	private Date stopDate;
	
	private int taskState;
	
	private boolean running;
	
	private String ip;
	
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public AbstractTaskMonitor() {
		super();
	}

	public String getTaskClassName() {
		
		return this.getClass().getCanonicalName();
	}

	public String getTaskMoudle() {
		
		return taskMoudle;
	}

	public Date getStartDate() {
		
		return startDate;
	}

	public int getTaskState() {
		
		return taskState;
	}

	public Date getStopDate() {
		
		return stopDate;
	}

	public long getHasExcutedTime()throws Exception {
		if(null == startDate){
			throw new Exception("Task not start yet!");
		}
		
		return new Date().getTime()-startDate.getTime();
	}

	protected void setTaskMoudle(String taskMoudle) {
		
		this.taskMoudle = taskMoudle;
	}

	protected void setStartDate(Date startDate) {
		
		this.startDate = startDate;
	}

	protected void setStopDate(Date stopDate) {
		
		this.stopDate = stopDate;
	}

	protected void setTaskState(int taskState) {
		
		this.taskState = taskState;
	}
	
	protected void reset() {
		this.setTaskMoudle(null);
		this.setStartDate(null);
		this.setStopDate(null);
		this.setTaskState(0);
	}

	public String getThreadName() {
	    
	    return Thread.currentThread().getName();
    }
	
	public TaskState getStateEntity() {
		TaskState state = new TaskState();
		try {
			state.setHasExcutedTime(this.getHasExcutedTime());
		} catch (Exception e) {
			//nothing to do
		}
		state.setStartDate(this.getStartDate());
		state.setStopDate(this.getStopDate());
		state.setTaskClassName(this.getTaskClassName());
		state.setTaskId(this.getTaskId());
		state.setTaskMoudle(this.getTaskMoudle());
		state.setTaskState(this.getTaskState());
		state.setThreadName(this.getThreadName());
		state.setRunning(this.isRunning());
		state.setIp(NetUtil.getIp());
		setStateEntity(state);
		return state;
	}
	
	protected abstract void setStateEntity(TaskState state);

	protected void init() {
		startDate = new Date();
	}
}
