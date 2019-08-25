package org.pretend.monitor.task.service;

import java.util.List;

import org.pretend.monitor.api.entity.TaskState;

public interface TaskMonitorService {

	
	public List<TaskState> getTaskList();
	
}
