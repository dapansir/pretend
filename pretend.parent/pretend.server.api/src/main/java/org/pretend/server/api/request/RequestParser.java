package org.pretend.server.api.request;

import org.pretend.server.api.ParseResult;

public interface RequestParser {
	
	ParseResult parseRequest(Object message);
	
}
