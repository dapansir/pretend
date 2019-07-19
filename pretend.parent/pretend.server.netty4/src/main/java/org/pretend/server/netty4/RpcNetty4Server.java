package org.pretend.server.netty4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.pretend.server.netty4.handler.rpc.InternalDecoder;

public class RpcNetty4Server {
	
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
							.addLast("decoder", new InternalDecoder());
					}
				});
		
		try {
			ChannelFuture channelFuture = server.bind(20880).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new RpcNetty4Server().start();
	}

}
