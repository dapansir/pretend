package org.pretend.remoting.api.abs;

import org.pretend.remoting.api.RemotingException;
import org.pretend.remoting.api.interfaces.Channel;
import org.pretend.remoting.api.interfaces.ChannelHandler;

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
