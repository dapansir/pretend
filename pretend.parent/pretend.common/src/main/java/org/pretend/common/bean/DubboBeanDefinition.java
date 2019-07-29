package org.pretend.common.bean;

import java.util.Collection;
import java.util.Map;

import org.pretend.common.bean.api.FieldAccessor;


public class DubboBeanDefinition extends BeanDefinition {

	
	public DubboBeanDefinition(Object source){
		super(source);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -2374821574899694645L;
	
	
	@Override
    protected void dealField(FieldAccessor fieldAccessor, Map<String, Object> attributes) {
		
		Class<?> type = fieldAccessor.fieldType();
		
		if (Map.class.isAssignableFrom(type) || Collection.class.isAssignableFrom(type)) {
			super.dealField(fieldAccessor, attributes);
		}
		
    }
	
	
}
