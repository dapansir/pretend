package org.pretend.remoting.api.abs;

import org.pretend.common.Parameter;
import org.pretend.remoting.api.RemotingException;
import org.pretend.remoting.api.interfaces.Channel;
import org.pretend.remoting.api.interfaces.ChannelHandler;
import org.pretend.remoting.api.interfaces.Endpoint;

public abstract class AbstractPeer implements Endpoint,ChannelHandler {
	
	private Parameter parameter;
	
	private ChannelHandler handler;
	
	private boolean closed;
	
	private boolean closing;
	
	
	public AbstractPeer(Parameter parameter,ChannelHandler handler){
		
		this.parameter = parameter;
		this.handler = handler;
	}

	@Override
	public void connected(Channel channel) throws RemotingException {
		handler.connected(channel);
	}

	@Override
	public void disconnected(Channel channel) throws RemotingException {
		handler.disconnected(channel);
	}

	@Override
	public void sent(Channel channel, Object message) throws RemotingException {
		if(closed){
			return;
		}
		handler.sent(channel, message);
	}

	@Override
	public void received(Channel channel, Object message)
			throws RemotingException {
		handler.received(channel, message);
	}

	@Override
	public void caught(Channel channel, Throwable exception)
			throws RemotingException {
		handler.caught(channel, exception);
	}
	
	@Override
	public Parameter getParameter(){
		return this.parameter;
	}
	
	@Override
	public ChannelHandler getChannelHandler() {
		
		return this.handler;
	}
	
	@Override
	public void close() {
		
		closed = true;
	}

	@Override
	public void close(int timeout) {
		
		close();
	}

	@Override
	public void startClose() {
		if (isClosed()) {
            return;
        }
        closing = true;
	}

	@Override
	public boolean isClosed() {
		
		return closed;
	}
	
    public boolean isClosing() {
        return closing && !closed;
    }
    
    @Override
	public void send(Object message) throws RemotingException {
    	send(message, parameter.get("sent", false));
	}

}
