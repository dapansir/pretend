package org.pretend.remoting.dubbo.service;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ModuleConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;

public interface ConfigService {
	
	ConsumerConfig getConsumerConfig();
	
	ProviderConfig getProviderConfig();
	
	ApplicationConfig getApplicationConfig();
	
	ModuleConfig getModuleConfig();
	
	MonitorConfig getMonitorConfig();
	
	RegistryConfig getRegistryConfig();
	
	ProtocolConfig getProtocolConfig();
	
	@SuppressWarnings("rawtypes")
	ReferenceConfig getReferenceConfig();
	
	@SuppressWarnings("rawtypes")
	ServiceConfig getServiceConfig();
	
	AnnotationBean getAnnotationConfig();
	
	
}
