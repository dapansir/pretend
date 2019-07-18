package org.pretend.tools.namespace;

import org.pretend.tools.config.ProtocolConfig;
import org.pretend.tools.config.ThreadPoolConfig;
import org.pretend.tools.parse.element.PretendParser;


public class PretendNameSpaceHandler extends AbstractNameSpaceHandler {
	 
	static{
		registerParser("threadpool", new PretendParser(ThreadPoolConfig.class));
		registerParser("protocol", new PretendParser(ProtocolConfig.class));
	}
	
}
