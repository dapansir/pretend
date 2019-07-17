package org.pretend.tools.parse.element;

import org.pretend.common.bean.BeanDefinition;
import org.pretend.tools.api.Parser;
import org.pretend.tools.constant.PretendTagName;
import org.pretend.tools.parse.AbstractParser;
import org.w3c.dom.Element;

public class ProtocolParser extends AbstractParser implements Parser {

	private final String regexpStr = "-";
	
	@Override
	public BeanDefinition parse(Element element) {
		if(!PretendTagName.PROTOCOL.equals(element.getLocalName())){
			throw new IllegalArgumentException("The element is not a protocol element!");
		}
		System.out.println();
		return null;
	}

	@Override
	protected String getSplitRegexp() {
		return regexpStr;
	}

}
