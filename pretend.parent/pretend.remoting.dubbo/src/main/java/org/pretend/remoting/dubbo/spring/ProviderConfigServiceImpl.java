package org.pretend.remoting.dubbo.spring;

import java.util.Map;

import org.pretend.remoting.api.monitor.ConfigService;
import org.springframework.beans.factory.BeanFactoryUtils;

import com.alibaba.dubbo.config.ProviderConfig;

public class ProviderConfigServiceImpl extends AbstractConfigService implements ConfigService<ProviderConfig> {

	@SuppressWarnings("unchecked")
	@Override
	public ProviderConfig getConfig(Class<ProviderConfig> clazz) {
		ProviderConfig providerConifg = null;
		Map<String,ProviderConfig> configs = BeanFactoryUtils.beansOfTypeIncludingAncestors(getApplicationContext(), clazz,false,false);
		if(null != configs && !configs.isEmpty()) {
			for (ProviderConfig config : configs.values()) {
				providerConifg = config;
			}
		}
		return providerConifg;
	}

}
