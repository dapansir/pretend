package org.pretend.tools.parse.element;

import org.pretend.tools.api.Parser;
import org.pretend.tools.parse.AbstractParser;

public class PretendParser extends AbstractParser implements Parser {

	private Class<?> clazz;

	private final String regexpStr = "_";

	public PretendParser(Class<?> clazz) {
		super();
		this.clazz = clazz;
	}

	@Override
	protected String getSplitRegexp() {
		return regexpStr;
	}
	
	@Override
	protected Class<?> getClazz(){
		return clazz;
	}
	
}
