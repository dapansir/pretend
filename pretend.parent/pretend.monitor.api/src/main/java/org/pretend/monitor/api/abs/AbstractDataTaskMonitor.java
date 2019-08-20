package org.pretend.monitor.api.abs;

import java.util.Date;

import org.pretend.monitor.api.DataTaskMonitor;

public abstract class AbstractDataTaskMonitor extends AbstractTaskMonitor implements DataTaskMonitor {

	private long dataConut;
	
	private long dealedDataCount;

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
	
	
}
