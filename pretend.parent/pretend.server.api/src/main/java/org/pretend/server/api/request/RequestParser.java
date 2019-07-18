package org.pretend.server.api.request;

import org.pretend.server.api.invocation.PretendInvocation;

public interface RequestParser {
	
	PretendInvocation parseRequest(Object message);
	
}
