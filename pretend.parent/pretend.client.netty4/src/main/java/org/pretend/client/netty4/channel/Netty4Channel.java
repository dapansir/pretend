package org.pretend.client.netty4.channel;

import java.net.InetSocketAddress;

import org.pretend.client.api.ChannelHandler;
import org.pretend.client.api.RemotingException;

import io.netty.channel.Channel;

public class Netty4Channel extends AbstractChannel {
	
	private final Channel channel;
	
	private boolean closed;
	
	private String name;
	
	
	public Netty4Channel(String channelName,Channel channel) {
		setChannelName(channelName);
		this.channel = channel;
	}

	@Override
	public boolean isConnected() {
		
		return !closed && channel.isActive();
	}

	@Override
	protected void setChannelName(String channelName) {
		this.name = channelName;
		setAttribute(CHANNEL_NAME_STR, this.name);
	}
	
	@Override
	public InetSocketAddress getRemoteAddress() {
		
		return (InetSocketAddress) channel.remoteAddress();
	}

	@Override
	public ChannelHandler getChannelHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InetSocketAddress getLocalAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void send(Object message) throws RemotingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(Object message, boolean sent) throws RemotingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close(int timeout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startClose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isClosed() {
		// TODO Auto-generated method stub
		return false;
	}

	

}
