package org.pretend.tools.namespace;

import org.pretend.tools.parse.element.ProtocolParser;
import org.pretend.tools.parse.element.ThreadPoolParser;


public class PretendNamaSpaceHandler extends AbstractNameSpaceHandler {
	 
	static{
		registerParser("threadpool", new ThreadPoolParser());
		registerParser("protocol", new ProtocolParser());
	}
	
}
