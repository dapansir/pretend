package org.pretend.web.controller;

import org.pretend.remoting.dubbo.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.fastjson.JSON;


@RequestMapping("/config")
@Controller
public class ConfigController {
	
	@Autowired
	private ConfigService configService;
	
	@RequestMapping("/consumer")
	@ResponseBody
	public String getConsumerConfig(){
		ConsumerConfig consumerConfig = configService.getConsumerConfig();
		return JSON.toJSONString(consumerConfig);
	}
}
