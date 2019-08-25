package org.pretend.monitor.task.service.impl;

import java.util.List;

import org.pretend.monitor.api.abs.AbstractDefaultTaskMonitor;
import org.pretend.monitor.api.entity.TaskState;
import org.pretend.monitor.task.service.TaskMonitorService;

public class TaskMonitorServiceImpl implements TaskMonitorService {

	@Override
	public List<TaskState> getTaskList() {
		
		return AbstractDefaultTaskMonitor.getTaskList();
	}

}
