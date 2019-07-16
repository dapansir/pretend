package org.pretend.invoke.service.impl;

import org.pretend.common.loader.ExtensionLoader;
import org.pretend.common.logger.Logger;
import org.pretend.common.logger.LoggerFactory;
import org.pretend.invoke.service.config.ConfigService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.AbstractConfig;

@Service("configService")
public class ConfigServiceImpl implements ConfigService,ApplicationContextAware,InitializingBean {

	private ApplicationContext springContext;
	
	private static final LoggerFactory LOGGER_FACTORY = ExtensionLoader.getExtensionLoader(LoggerFactory.class).getActiveExtension();
	
	private static final Logger LOGGER = LOGGER_FACTORY.getLogger(ConfigServiceImpl.class);

	public void afterPropertiesSet() throws Exception {
		
	}

	public void setApplicationContext(ApplicationContext springContext)
			throws BeansException {
		LOGGER.info("开始设置Spring ApplicationContext!");
		this.springContext = springContext;
		LOGGER.info("设置Spring ApplicationContext结束");
	}

	public <T> T getConfig(Class<T> clazz) {
		T t = null;
		if(null == clazz){
			LOGGER.error("参数为空");
			return t;
		}
		if(null == springContext){
			LOGGER.error("没有设置springcontex!t");
			return t;
		}
		if(!AbstractConfig.class.isAssignableFrom(clazz)){
			LOGGER.error("传入的参数不是AbstractConfig类型或者其子类!");
			return t;
		}
		try {
			t = BeanFactoryUtils.beanOfType(springContext, clazz, false, false);
		} catch (Exception e) {
			LOGGER.error("该类型的ConfigBean未注册!",e);
		}
		return t;
	}

}
