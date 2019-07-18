package org.pretend.common.bean.abs;

import java.io.Serializable;

import org.pretend.common.bean.api.BeanMetadataInfo;

public class AbstractBeanDefinition extends AbstarctAttribueAccessor implements BeanMetadataInfo,Serializable{

	private Object source;
	
	private Class<?> beanClass;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Object getSource() {
		return source;
	}
	
	public void setSource(Object source){
		this.source = source;
	}

	public Class<?> getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}
	
	public String getBeanClassName(){
		return this.beanClass.getName();
	}

	@Override
	public String toString() {
		return "AbstractBeanDefinition [source=" + source + ", beanClass="
				+ beanClass + "]";
	}
	
	

}
