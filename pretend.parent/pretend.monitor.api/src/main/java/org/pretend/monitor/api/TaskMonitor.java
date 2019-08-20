package org.pretend.monitor.api;

import java.util.Date;

public interface TaskMonitor {
	/**
	 * 任务用途
	 * @return
	 */
	String getTaskDescription();
	/**
	 * 执行任务的类名
	 * @return
	 */
	String getTaskClassName();
	/**
	 * 任务编号
	 * @return
	 */
	String getTaskId();
	/**
	 * 任务执行模式
	 * @return
	 */
	String getTaskMoudle();
	/**
	 * 任务开始执行日期
	 * @return
	 */
	Date getStartDate();
	/**
	 * 任务状态
	 * @return
	 */
	int getTaskState();
	
	/**
	 * 任务结束日期
	 * @return
	 */
	Date getStopDate();
	
	/**
	 * 目前执行时长
	 * @return
	 */
	long getHasExcutedTime()throws Exception;
	/**
	 * 执行过程中的 异常信息
	 * @return
	 */
	Object getExceptionMsg();
	/**
	 * 获取当前线程名称
	 * @return
	 */
	String getThreadName();
}
