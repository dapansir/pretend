package org.pretend.monitor.api;

import java.util.Date;

public interface DataTaskMonitor extends TaskMonitor {
	
	/**
	 * 处理数据总数
	 * @return
	 */
	long getDataCount();
	/**
	 * 已经处理数据总数
	 * @return
	 */
	long getDealedDataCount();
	/**
	 * 待数据数据总数
	 * @return
	 */
	long getUnDealedDataCount();
	/**
	 * 当前平均执行时间
	 * @return
	 */
	long averageTime();
	/**
	 * 预计还需执行时长
	 * @return
	 */
	long getEstimatedTime();
	/**
	 * 预计任务结束时间
	 * @return
	 */
	Date getEstimatedEndDate();
}
