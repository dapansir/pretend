package org.pretend.monitor.api.abs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.pretend.monitor.api.DefaultTaskMonitor;
import org.pretend.monitor.api.entity.TaskState;

public abstract class AbstractDefaultTaskMonitor extends AbstractTaskMonitor implements DefaultTaskMonitor {

	private long dataConut;
	
	private long dealedDataCount;

	private static final Map<String,AbstractDefaultTaskMonitor> THREADS = new ConcurrentHashMap<String,AbstractDefaultTaskMonitor>();
	
	public AbstractDefaultTaskMonitor() {
		super();
		registry();
	}
	
	private void registry() {
		THREADS.put(getTaskId(), this);
	}

	public long getDataCount() {
		
		return dataConut;
	}

	public long getDealedDataCount() {
		
		return dealedDataCount;
	}

	public long getUnDealedDataCount() {
		
		return dataConut-dealedDataCount;
	}

	public long averageTime() {
		if(dealedDataCount != 0){
			try {
	            long hasExcutedTime = getHasExcutedTime();
	            return hasExcutedTime/dealedDataCount;
            } catch (Exception e) {
            	//nothing to do
            }
        }
		return 0;
	}

	public long getEstimatedTime() {
		
		return getUnDealedDataCount()*averageTime();
	}

	public Date getEstimatedEndDate() {
		long time = new Date().getTime()+getEstimatedTime();
		return new Date(time);
	}

	protected void setDataConut(long dataConut) {
		this.dataConut = dataConut;
	}

	protected void setDealedDataCount(long dealedDataCount) {
		this.dealedDataCount = dealedDataCount;
	}
	/**
	 * 已处理数据的计数器+1
	 */
	protected void increment(){
		this.dealedDataCount = getDealedDataCount()+1;
	}
	/**
	 * 待处理数据的计数器-1
	 */
	protected void deIncrement(){
		this.dealedDataCount = getUnDealedDataCount()-1;
	}

	@Override
    protected void reset() {
	    super.reset();
	    this.setDataConut(0);
	    this.setDealedDataCount(0);
    }

	public static List<TaskState> getTaskList(){
		List<TaskState> states = new ArrayList<TaskState>();
		for (AbstractDefaultTaskMonitor task : THREADS.values()) {
			states.add(task.getStateEntity());
		}
		return states;
	}

	@Override
	protected void setStateEntity(TaskState state) {
		state.setAverageTime(this.averageTime());
		state.setDataConut(this.getDataCount());
		state.setDealedDataCount(this.getDealedDataCount());
		state.setEstimatedEndDate(this.getEstimatedEndDate());
		state.setEstimatedTime(this.getEstimatedTime());
	}

	@Override
	protected void init() {
		super.init();
	}
	
	
	
}
