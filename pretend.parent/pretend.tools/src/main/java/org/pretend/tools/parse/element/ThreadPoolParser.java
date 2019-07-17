package org.pretend.tools.parse.element;

import org.pretend.common.bean.BeanDefinition;
import org.pretend.tools.api.Parser;
import org.pretend.tools.constant.PretendTagName;
import org.w3c.dom.Element;

public class ThreadPoolParser implements Parser {

	
	
	@Override
	public BeanDefinition parse(Element element) {
		if(!PretendTagName.THREAD_POOL.equals(element.getLocalName())){
			throw new IllegalArgumentException("The element is not a threadpool element!");
		}
		
		return null;
	}

}
