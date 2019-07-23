package org.pretend.client.netty4;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import org.pretend.client.api.ChannelHandler;
import org.pretend.client.api.RemotingException;
import org.pretend.client.netty4.http.HttpClientHandler;
import org.pretend.common.Parameter;

public class NettyClient extends AbstractClient {
	
	private ChannelHandler handler;
	
	private Channel channel;
	
	private Bootstrap bootstrap = new Bootstrap(); // (1)
	
	@Override
	protected void doOpen() {
		String host = "localhost";
		int port = Integer.parseInt("20880");
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			bootstrap.group(workerGroup); // (2)
			bootstrap.channel(NioSocketChannel.class); // (3)
			bootstrap.option(ChannelOption.SO_KEEPALIVE, true); // (4)
			bootstrap.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new HttpClientHandler());
				}
			});
			// 启动客户端
			ChannelFuture channelFuture= b.connect(host, port).sync(); // (5)
			channel = channelFuture.channel();
			// 等待连接关闭
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doConnect() {
		/*ChannelFuture channelFuture= bootstrap.connect(getParameter().get("host"), getParameter().get("port", 8090)).sync();
		boolean success = false;
		try {
			int timeout = getParameter().get("timeout", 10000);
			success = future.await(timeout, TimeUnit.MILLISECONDS);
			Throwable cause = future.cause();
			if(null != cause){
				throw cause;
			}
		} catch (InterruptedException e) {
			throw new IllegalStateException("发送消息时出现异常");
		}
		if(!success){
			throw new IllegalStateException("发送消息失败");
		}*/
	}
	
	public NettyClient(ChannelHandler handler,Parameter parameter){
		super(handler, parameter);
	}

	@Override
	public InetSocketAddress getRemoteAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasAttribute(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getAttribute(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(String key, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object removeAttribute(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getChannelName() {
		// TODO Auto-generated method stub
		return null;
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


	@Override
	protected org.pretend.client.api.Channel getChannel() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
