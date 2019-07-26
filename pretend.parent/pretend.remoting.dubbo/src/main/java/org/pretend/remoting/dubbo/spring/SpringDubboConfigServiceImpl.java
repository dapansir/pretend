package org.pretend.remoting.dubbo.spring;

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

public class SpringDubboConfigServiceImpl extends AbstractConfigService {

	@Override
	public ConsumerConfig getConsumerConfig() {

		return getConfig(ConsumerConfig.class);
	}

	@Override
	public ProviderConfig getProviderConfig() {

		return getConfig(ProviderConfig.class);
	}

	@Override
	public ApplicationConfig getApplicationConfig() {
		
		return getConfig(ApplicationConfig.class);
	}

	@Override
	public ModuleConfig getModuleConfig() {
		
		return getConfig(ModuleConfig.class);
	}

	@Override
	public MonitorConfig getMonitorConfig() {
		
		return getConfig(MonitorConfig.class);
	}

	@Override
	public RegistryConfig getRegistryConfig() {
		
		return getConfig(RegistryConfig.class);
	}

	@Override
	public ProtocolConfig getProtocolConfig() {
		
		return getConfig(ProtocolConfig.class);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ReferenceConfig getReferenceConfig() {

		return getConfig(ReferenceConfig.class);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ServiceConfig getServiceConfig() {

		return getConfig(ServiceConfig.class);
	}

	@Override
	public AnnotationBean getAnnotationConfig() {
		
		return getConfig(AnnotationBean.class);
	}

}
