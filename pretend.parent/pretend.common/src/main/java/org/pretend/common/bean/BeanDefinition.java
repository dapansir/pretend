package org.pretend.common.bean;

import java.util.Map;

import org.pretend.common.bean.abs.AbstractBeanDefinition;
import org.pretend.common.bean.api.FieldAccessor;
import org.pretend.common.util.ObjectUtil;

public class BeanDefinition extends AbstractBeanDefinition {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5191149325846168950L;

	public BeanDefinition() {
		
	}
	
	public BeanDefinition(Object source) {
		super();
		ObjectUtil.notNull(source, "source must not be null!");
		setBeanClass(source.getClass());
		setSource(source);
	}
	
	@Override
    protected void dealField(FieldAccessor fieldAccessor, Map<String, Object> attributes) {
		attributes.put(fieldAccessor.name(), fieldAccessor.value());
    }
	
}
