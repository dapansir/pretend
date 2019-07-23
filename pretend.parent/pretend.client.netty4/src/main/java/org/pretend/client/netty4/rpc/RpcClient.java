package org.pretend.client.netty4.rpc;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.pretend.client.netty4.http.HttpClientHandler;


public class RpcClient {
	
	private Channel channel;
	
	public RpcClient(){
		String host = "localhost";
		int port = Integer.parseInt("20880");
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			Bootstrap b = new Bootstrap(); // (1)
			b.group(workerGroup); // (2)
			b.channel(NioSocketChannel.class); // (3)
			b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
			b.handler(new ChannelInitializer<SocketChannel>() {
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
		} finally {
			workerGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws Exception {

		
	}
	
	public void send(Object msg,int timeout) throws Throwable{
		ChannelFuture future = channel.writeAndFlush(msg);
		boolean success = false;
		try {
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
		}
		
	}
	
	
	protected Channel getChannel(){
		return this.channel;
	}
}
