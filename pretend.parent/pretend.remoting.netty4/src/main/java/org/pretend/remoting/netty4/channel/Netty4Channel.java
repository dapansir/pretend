package org.pretend.remoting.netty4.channel;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.pretend.common.Parameter;
import org.pretend.remoting.api.RemotingException;
import org.pretend.remoting.api.abs.AbstractChannel;
import org.pretend.remoting.api.interfaces.ChannelHandler;

public class Netty4Channel extends AbstractChannel {
	
	private final Channel channel;
	
	private boolean closed;
	
	private String name;
	
	private static Map<Channel, Netty4Channel> channelMap = new ConcurrentHashMap<Channel, Netty4Channel>();
	
	public Netty4Channel(String channelName,Channel channel,Parameter parameter,ChannelHandler handler) {
		super(parameter, handler);
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
	public InetSocketAddress getLocalAddress() {
		
		return (InetSocketAddress) channel.localAddress();
	}

	@Override
	public void send(Object message, boolean sent) throws RemotingException {
		
		
	}


	public static Netty4Channel getOrAddChannel(String channelName,Channel key,Parameter parameter,ChannelHandler handler){
		if(null == key){
			return null;
		}
		Netty4Channel netty4Channel = channelMap.get(key);
		if(null == netty4Channel){
			netty4Channel = new Netty4Channel(channelName, key, parameter, handler);
			if(key.isActive()){
				channelMap.put(key, netty4Channel);
			}
		}
		return netty4Channel;
	}
	
	public static void removeChannelIfDisconnected(Channel channel){
		if(null != channel && !channel.isActive()){
			channelMap.remove(channel);
		}
	}

}
