package org.pretend.common.bean;

import org.pretend.common.bean.abs.AbstractBeanDefinition;

public class BeanDefinition extends AbstractBeanDefinition {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5191149325846168950L;

	public BeanDefinition() {
		
	}
	public BeanDefinition(Object source) {
		super();
		setBeanClass(source.getClass());
		setSource(source);
	}
}
