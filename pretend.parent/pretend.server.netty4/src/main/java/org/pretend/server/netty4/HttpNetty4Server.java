package org.pretend.server.netty4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import org.pretend.server.api.Server;
import org.pretend.server.netty4.handler.Nett4ServerHttpDecoder;
import org.pretend.server.netty4.handler.Nett4ServerHttpEncoder;

public class HttpNetty4Server implements Server{

	@Override
	public void start() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		ServerBootstrap  server = new ServerBootstrap();
		server.group(bossGroup, workGroup)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.TCP_NODELAY, false)
				.option(ChannelOption.SO_BACKLOG, 1024)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline()
							.addLast("http_encoder", new HttpResponseEncoder())
							.addLast("my_encoder", new Nett4ServerHttpEncoder())
							.addLast("http_decoder",new HttpRequestDecoder())
							.addLast("my_decoder",new Nett4ServerHttpDecoder());
					}
				});
		
		
		try {
			ChannelFuture channelFuture = server.bind(8090).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void stop() {
		
	}

	@Override
	public Object getState() {
		
		return null;
	}
	
	public static void main(String[] args) {
		HttpNetty4Server server = new HttpNetty4Server();
		server.start();
	}

}
