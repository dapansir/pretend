package org.pretend.remoting.api.abs;


import java.net.InetSocketAddress;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.pretend.common.Parameter;
import org.pretend.remoting.api.interfaces.Channel;
import org.pretend.remoting.api.interfaces.ChannelHandler;
import org.pretend.remoting.api.interfaces.Client;
import org.pretend.remoting.api.interfaces.RemotingException;



public abstract class AbstractClient extends AbstractEndpoint implements Client{
	
	
	private final Lock connectLock = new ReentrantLock();
	
	private final boolean send_reconnect;
	
	
	public AbstractClient(ChannelHandler handler,Parameter parameter){
		super(parameter, handler);
		send_reconnect = false;
	}
	
	
	public void send(Object msg,boolean sent) throws RemotingException{
		try {
			connectLock.lock();
			if (send_reconnect && isConnected()) {
				doConnect();
			}
			Channel channel = getChannel();
			if (null == channel) {
				throw new RemotingException(channel, "the method getChannel() cannot return a null value!");
			}
			channel.send(msg, sent);
		}catch(RemotingException e){
			throw new RemotingException(this, "error occurs when send message to remote host!");
		}catch (Throwable e) {
			throw new RemotingException(this, "error occurs when connect to remote host!");
		}finally{
			connectLock.unlock();
		}
	}
	
	@Override
	public boolean hasAttribute(String key) {
		Channel channel = getChannel();
		if(null == channel){
			return false;
		}
		return channel.hasAttribute(key);
	}

	@Override
	public Object getAttribute(String key) {
		Channel channel = getChannel();
		if(null == channel){
			return null;
		}
		return channel.getAttribute(key);
	}

	@Override
	public void setAttribute(String key, Object value) {
		Channel channel = getChannel();
		if(null == channel){
			return;
		}
		channel.setAttribute(key, value);
	}

	@Override
	public Object removeAttribute(String key) {
		Channel channel = getChannel();
		if(null == channel){
			return null;
		}
		return channel.removeAttribute(key);
	}
	
	@Override
	public InetSocketAddress getRemoteAddress() {
		Channel channel = getChannel();
		if(null == channel){
			return null;
		}
		return (InetSocketAddress) channel.getRemoteAddress();
	}
	
	@Override
	public InetSocketAddress getLocalAddress() {
		Channel channel = getChannel();
		if(null == channel){
			return null;
		}
		return (InetSocketAddress) channel.getLocalAddress();
	}
	
	@Override
	public boolean isConnected() {
		Channel channel = getChannel();
		if(null == channel){
			return false;
		}
		return channel.isConnected();
	}
	
	@Override
	public String getChannelName() {
		Channel channel = getChannel();
		if(null == channel){
			return null;
		}
		return channel.getChannelName();
	}
	
	@Override
	public void reconnect() throws RemotingException {
		try {
			connectLock.lock();
			doDisConnect();
			doConnect();
		}catch (Throwable e) {
			throw new RemotingException(this, "reconnect to server failed", e);
		}finally{
			connectLock.unlock();
		}
		
	}
	
	
	protected abstract void doOpen()throws Throwable ;
	
	protected abstract void doConnect()throws Throwable ;
	
	protected abstract void doDisConnect()throws Throwable ;
	
	protected abstract Channel getChannel();
	
}
