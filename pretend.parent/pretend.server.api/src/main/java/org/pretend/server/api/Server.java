package org.pretend.server.api;

public interface Server {
	
	void start();
	
	void stop();
	
	Object getState();
	
	
}
