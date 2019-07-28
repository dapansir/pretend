package org.pretend.remoting.dubbo.service;

import java.util.List;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ModuleConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.alibaba.dubbo.config.spring.ServiceBean;

public interface ConfigService {
	
	ConsumerConfig getConsumerConfig();
	
	@SuppressWarnings("rawtypes")
	ServiceBean getProviderConfig();
	
	ApplicationConfig getApplicationConfig();
	
	ModuleConfig getModuleConfig();
	
	MonitorConfig getMonitorConfig();
	
	RegistryConfig getRegistryConfig();
	
	ProtocolConfig getProtocolConfig();
	
	@SuppressWarnings("rawtypes")
	ReferenceBean getReferenceConfig();
	
	@SuppressWarnings("rawtypes")
	ServiceConfig getServiceConfig();
	
	AnnotationBean getAnnotationConfig();
	
	<T> List<T> getConfigList(Class<T> clazz);
}
