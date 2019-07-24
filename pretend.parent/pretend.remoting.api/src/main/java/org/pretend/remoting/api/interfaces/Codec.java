package org.pretend.remoting.api.interfaces;

public interface Codec {

	void encode(Channel channel,Object msg);
	
	void decode(Channel channel,Object msg);
	
}
