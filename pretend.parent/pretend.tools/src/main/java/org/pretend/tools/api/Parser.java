package org.pretend.tools.api;

import org.pretend.common.bean.BeanDefinition;
import org.w3c.dom.Element;


public interface Parser {
	/**
	 * 解析xml中的element
	 * @param element  xml element
	 * @return
	 */
	BeanDefinition parse(Element element);
	
}
