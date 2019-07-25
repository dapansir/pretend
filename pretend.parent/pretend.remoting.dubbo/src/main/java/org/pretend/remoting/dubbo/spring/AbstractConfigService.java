package org.pretend.remoting.dubbo.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;

public class AbstractConfigService implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		this.applicationContext = applicationContext;
	}

	protected ApplicationContext getApplicationContext() {
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
}
