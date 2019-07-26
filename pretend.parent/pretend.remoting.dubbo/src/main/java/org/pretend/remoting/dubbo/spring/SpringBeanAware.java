package org.pretend.remoting.dubbo.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringBeanAware implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		SpringBeanAware.applicationContext = applicationContext;
	}
	
	public static void awareContext(ApplicationContextAware aware){
		
		aware.setApplicationContext(applicationContext);
	}
	

}
