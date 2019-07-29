package org.pretend.web.controller;

import org.pretend.common.bean.BeanDefinition;
import org.pretend.common.bean.DubboBeanDefinition;
import org.pretend.remoting.dubbo.service.ConfigService;
import org.pretend.web.controller.serialize.SerializeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.alibaba.fastjson.JSON;


@RequestMapping("/config")
@Controller
public class ConfigController {
	
	@Autowired
	private ConfigService configService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/provider")
	@ResponseBody
	public String getConsumerConfig(){
		ServiceBean providerConfig = configService.getProviderConfig();
		BeanDefinition bean = new DubboBeanDefinition(providerConfig);
		return JSON.toJSONString(bean.getAttributes(),new SerializeFilter());
	}
	
	@RequestMapping("/registry")
	@ResponseBody
	public String getRegistryConfig(){
		RegistryConfig registryConfig = configService.getRegistryConfig();
		return JSON.toJSONString(registryConfig);
	}
	
	@RequestMapping("/application")
	@ResponseBody
	public String getApplicationConfig(){
		ApplicationConfig applicationConfig = configService.getApplicationConfig();
		BeanDefinition bean = new BeanDefinition(applicationConfig);
		return JSON.toJSONString(bean.getAttributes());
	}
	
	
}
