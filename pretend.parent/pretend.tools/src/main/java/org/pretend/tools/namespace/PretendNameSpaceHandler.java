package org.pretend.tools.namespace;

import org.pretend.tools.config.ProtocolConfig;
import org.pretend.tools.config.ThreadPoolConfig;
import org.pretend.tools.parse.element.ElementParser;


public class PretendNameSpaceHandler extends AbstractNameSpaceHandler {
	 
	static{
		registerParser("threadpool", new ElementParser(ThreadPoolConfig.class));
		registerParser("protocol", new ElementParser(ProtocolConfig.class));
	}
	
}
