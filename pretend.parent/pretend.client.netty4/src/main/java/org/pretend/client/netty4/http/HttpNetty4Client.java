package org.pretend.client.netty4.http;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.pretend.client.api.Client;

public class HttpNetty4Client implements Client {

	private boolean connected = false;
	
	private boolean disConnected = false;
	
	private Bootstrap clientBootstrap;
	
	private NioEventLoopGroup worker = new NioEventLoopGroup();
	
	private ChannelFuture future;
	
	private String ip = "127.0.0.1";
	
	private int port = 8090;
	
	public HttpNetty4Client() {
		clientBootstrap = new Bootstrap();
		clientBootstrap.group(worker)
		.channel(NioSocketChannel.class)
		.option(ChannelOption.SO_KEEPALIVE, false)
		.option(ChannelOption.TCP_NODELAY, false)
		.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast("http-handler",new HttpClientHandler());
			}
		});
	}
	
	@Override
	public void connect() {
		if(!isConnect()){
			try {
				future = clientBootstrap.connect(ip, port).sync();
				connected = true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean isConnect() {
		
		return connected;
	}

	@Override
	public void send(Object message) {
		future.channel().writeAndFlush(message);
	}

	@Override
	public void recevice() {

	}

	@Override
	public void disconnect() {
		worker.shutdownGracefully();
		disConnected = true;
		connected = false;
	}

	@Override
	public boolean isDisconnect() {
		
		return disConnected;
	}

	
	public static void main(String[] args) {
		HttpNetty4Client client = new HttpNetty4Client();
		client.connect();
		client.send("aaaa");
	}
}
