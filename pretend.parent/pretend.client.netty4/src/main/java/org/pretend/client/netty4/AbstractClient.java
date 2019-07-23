package org.pretend.client.netty4;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.pretend.client.api.Channel;
import org.pretend.client.api.ChannelHandler;
import org.pretend.client.api.RemotingException;
import org.pretend.common.Parameter;



public abstract class AbstractClient implements org.pretend.client.api.Channel{
	
	
	private final Lock connectLock = new ReentrantLock();
	
	private final boolean send_reconnect;
	
	private ChannelHandler handler;
	
	private Parameter parameter;
	
	public AbstractClient(ChannelHandler handler,Parameter parameter){
		send_reconnect = false;
		this.handler = handler;
		this.parameter = parameter;
	}
	
	
	public void send(Object msg,boolean sent) throws RemotingException{
		if(send_reconnect && isConnected()){
			doConnect();
		}
		Channel channel = getChannel();
		if(null == channel){
			throw new RemotingException(channel, "the method getChannel() cannot return a null value!");
		}
		channel.send(msg, sent);
	}
	
	protected Parameter getParameter(){
		return this.parameter;
	}
	
	
	protected abstract void doOpen();
	
	protected abstract void doConnect();
	
	protected abstract Channel getChannel();
	
}
