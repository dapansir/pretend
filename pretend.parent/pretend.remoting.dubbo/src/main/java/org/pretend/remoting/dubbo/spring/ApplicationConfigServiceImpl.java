package org.pretend.remoting.dubbo.spring;

import java.util.Map;

import org.pretend.remoting.api.monitor.ConfigService;
import org.springframework.beans.factory.BeanFactoryUtils;

import com.alibaba.dubbo.config.ApplicationConfig;

public class ApplicationConfigServiceImpl extends AbstractConfigService implements ConfigService<ApplicationConfig> {

	@SuppressWarnings("unchecked")
	@Override
	public ApplicationConfig getConfig(Class<ApplicationConfig> clazz) {
		ApplicationConfig applicationConifg = null;
		Map<String,ApplicationConfig> configs = BeanFactoryUtils.beansOfTypeIncludingAncestors(getApplicationContext(), clazz,false,false);
		if(null != configs && !configs.isEmpty()) {
			for (ApplicationConfig config : configs.values()) {
				applicationConifg = config;
			}
		}
		return applicationConifg;
	}


}
