package org.pretend.remoting.dubbo.spring;

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

public class SpringDubboConfigServiceImpl extends AbstractConfigService {

	@Override
	public ConsumerConfig getConsumerConfig() {

		return getConfig(ConsumerConfig.class);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ServiceBean getProviderConfig() {

		return getConfig(ServiceBean.class);
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
	public ReferenceBean getReferenceConfig() {

		return getConfig(ReferenceBean.class);
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
