package org.pretend.client.netty4.handler;

import org.pretend.client.api.Channel;
import org.pretend.client.api.ChannelHandler;
import org.pretend.client.api.RemotingException;

public class AbstractChannelHandler implements ChannelHandler{

	@Override
	public void connected(Channel channel) throws RemotingException {
		
	}

	@Override
	public void disconnected(Channel channel) throws RemotingException {
		
	}

	@Override
	public void sent(Channel channel, Object message) throws RemotingException {
		
	}

	@Override
	public void received(Channel channel, Object message)
			throws RemotingException {
		
	}

	@Override
	public void caught(Channel channel, Throwable exception)
			throws RemotingException {
		
	}

}
