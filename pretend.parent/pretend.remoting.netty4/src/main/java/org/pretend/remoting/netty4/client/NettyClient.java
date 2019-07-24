package org.pretend.remoting.netty4.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.TimeUnit;

import org.pretend.common.Parameter;
import org.pretend.remoting.api.abs.AbstractClient;
import org.pretend.remoting.api.interfaces.ChannelHandler;
import org.pretend.remoting.api.interfaces.RemotingException;
import org.pretend.remoting.netty4.channel.Netty4Channel;

public class NettyClient extends AbstractClient {
	
	private volatile Channel channel;
	
	private Bootstrap bootstrap = new Bootstrap(); // (1)
	
	public NettyClient(ChannelHandler handler,Parameter parameter){
		super(handler, parameter);
	}
	
	@Override
	protected void doOpen()throws Throwable  {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		bootstrap.group(workerGroup); // (2)
		bootstrap.channel(NioSocketChannel.class); // (3)
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true); // (4)
		bootstrap.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel ch) throws Exception {
//				ch.pipeline().addLast(new HttpClientHandler());
			}
		});

	}

	@Override
	protected void doConnect() throws Throwable {
		ChannelFuture channelFuture= bootstrap.connect(getParameter().get("host"), getParameter().get("port", 8090)).sync();
		boolean success = false;
		int timeout = getParameter().get("timeout", 10000);
		try {
			success = channelFuture.awaitUninterruptibly(timeout, TimeUnit.MILLISECONDS);
			if (success && channelFuture.isSuccess()) {
				Channel newChannel = channelFuture.channel();
				Channel oldChannel = channel;//volatile,copy the reference!
				try {
					if (oldChannel != null) {
						oldChannel.close();
					}
				} finally {
					Netty4Channel.removeChannelIfDisconnected(oldChannel);
				}
				
				if(this.isClosed()){
					newChannel.close();
					Netty4Channel.removeChannelIfDisconnected(oldChannel);
				}else{
					this.channel = newChannel;
				}
			}else if(channelFuture.cause() != null){
				throw new RemotingException(this, "cannot connect to the remote host!", channelFuture.cause());
			}else{
				throw new RemotingException(this, "cannot connect to the remote host!", channelFuture.cause());
			}
			Throwable cause = channelFuture.cause();
			if(null != cause){
				throw cause;
			}
		} catch (InterruptedException e) {
			throw new IllegalStateException("发送消息时出现异常");
		}
		if(!success){
			throw new IllegalStateException("发送消息失败");
		}
	}
	
	@Override
	protected org.pretend.remoting.api.interfaces.Channel getChannel() {

		Channel channel = this.channel;//volatile,copy the reference
		if(null == channel || !channel.isActive()){
			return null;
		}
		return Netty4Channel.getOrAddChannel(getChannelName(), channel, getParameter(), this);
	}

	@Override
	protected void doDisConnect() throws Throwable {
		
	}

}
