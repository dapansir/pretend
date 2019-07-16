package org.pretend.web.controller;

import org.pretend.invoke.service.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.fastjson.JSONObject;


@RequestMapping("/config")
@Controller
public class ConfigController {
	
	@Autowired
	private ConfigService configServce;

	@ResponseBody
	@RequestMapping("/consumer")
	public String getConsumerConfig(){
		ConsumerConfig consumerConfig = configServce.getConfig(ConsumerConfig.class);
		return JSONObject.toJSONString(consumerConfig);
	}
}
