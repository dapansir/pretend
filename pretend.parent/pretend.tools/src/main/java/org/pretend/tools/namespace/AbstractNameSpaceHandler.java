package org.pretend.tools.namespace;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.pretend.tools.api.NameSpaceHandler;
import org.pretend.tools.api.Parser;

public abstract class AbstractNameSpaceHandler implements NameSpaceHandler{

	private static final Map<String,Parser> HANDLERS = new ConcurrentHashMap<String,Parser>();
	
	protected static Parser registerParser(String tagName,Parser parser){
		Parser temp = HANDLERS.get(tagName);
		if(null == temp){
			temp = HANDLERS.put(tagName, parser);
		}
		return temp;
	}
	
	
	@Override
	public Parser getParser(String tagName) {
		Parser parser = HANDLERS.get(tagName);
		if(null == parser){
			throw new IllegalStateException("Parser of tag["+tagName+"] not registered yet!");
		}
		return parser;
	}

}
