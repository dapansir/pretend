package org.pretend.web.controller.task;

import org.pretend.monitor.task.service.TaskMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/task")
public class TaskController {

	private PrintTask task = new PrintTask();
	@Autowired
	private TaskMonitorService taskMonitorService;
	
	@RequestMapping("/taskList")
	@ResponseBody
	public String taskLsit() {
		if(!task.isRunning()) {
			new Thread(task).start();
		}
		return JSON.toJSONString(taskMonitorService.getTaskList());
	}
}
