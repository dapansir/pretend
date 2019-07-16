package org.pretend.client.api;

public interface Client {
	
	void connect();
	
	boolean isConnect();
	
	void send(Object message);
	
	void recevice();
	
	void disconnect();
	
	boolean isDisconnect();
}
