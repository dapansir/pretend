package org.pretend.remoting.dubbo.spring;

import java.util.Map;

import org.pretend.remoting.api.monitor.ConfigService;
import org.springframework.beans.factory.BeanFactoryUtils;

import com.alibaba.dubbo.config.ConsumerConfig;

public class CosumerConfigServiceImpl extends AbstractConfigService implements ConfigService<ConsumerConfig> {

	@SuppressWarnings("unchecked")
	@Override
	public ConsumerConfig getConfig(Class<ConsumerConfig> clazz) {
		ConsumerConfig consumerConifg = null;
		Map<String,ConsumerConfig> configs = BeanFactoryUtils.beansOfTypeIncludingAncestors(getApplicationContext(), clazz,false,false);
		if(null != configs && !configs.isEmpty()) {
			for (ConsumerConfig config : configs.values()) {
				consumerConifg = config;
			}
		}
		return consumerConifg;
	}


}
