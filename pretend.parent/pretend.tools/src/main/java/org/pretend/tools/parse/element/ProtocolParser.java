package org.pretend.tools.parse.element;

import org.pretend.common.bean.BeanDefinition;
import org.pretend.tools.api.Parser;
import org.pretend.tools.constant.PretendTagName;
import org.w3c.dom.Element;

public class ProtocolParser implements Parser {

	@Override
	public BeanDefinition parse(Element element) {
		if(!PretendTagName.PROTOCOL.equals(element.getLocalName())){
			throw new IllegalArgumentException("The element is not a protocol element!");
		}
		return null;
	}

}
