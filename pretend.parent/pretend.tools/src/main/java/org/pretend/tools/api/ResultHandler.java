package org.pretend.tools.api;

import org.pretend.tools.entity.SendResult;

public interface ResultHandler {
	
	<T> void handler(Throwable e,SendResult<T> result);
	
	<T> void handler(SendResult<T> result);
}
