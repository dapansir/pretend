package org.pretend.remoting.dubbo.spring;

import java.util.Map;

import org.pretend.remoting.dubbo.service.ConfigService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;

public abstract class AbstractConfigService implements ApplicationContextAware,ConfigService{

	private ApplicationContext applicationContext;
	
	public AbstractConfigService() {
		super();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	protected ApplicationContext getApplicationContext()  {
		if(this.applicationContext == null ) {
			throw new IllegalStateException("applicationContext not set!");
		}
		if(applicationContext instanceof AbstractApplicationContext) {
			AbstractApplicationContext context = (AbstractApplicationContext)applicationContext;
			if(!context.isActive()) {
				throw new IllegalStateException("applicationContext is died!");
			}
		}
		return this.applicationContext;
	}
	
	protected <T> T getConfig(Class<T> clazz){
		T ret = null;
		Map<String, T> beanMap = getBeanMap(clazz);
		for (T t : beanMap.values()) {
			ret = t;
		}
		return ret;
	}
	
	protected <T> Map<String,T> getBeanMap(Class<T> clazz){
		return BeanFactoryUtils.beansOfTypeIncludingAncestors(getApplicationContext(), clazz,false,false);
	}
}
