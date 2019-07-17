package org.pretend.common.bean.abs;

import java.io.Serializable;

import org.pretend.common.bean.api.BeanMetadataInfo;

public class AbstractBeanDefinition extends AbstarctAttribueAccessor implements BeanMetadataInfo,Serializable{

	private Object source;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Object getSource() {
		return source;
	}

}
