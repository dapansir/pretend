package org.pretend.client.netty4.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HttpClientHandler extends ChannelInboundHandlerAdapter {
	
	

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String a = "aaaaaaaaaaaa";
		ByteBuf bf = ctx.alloc().buffer(4*a.length()); 
		bf.writeBytes(a.getBytes());
		ctx.writeAndFlush(bf);
//		super.channelActive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		ByteBuf byteBuf = (ByteBuf) msg;
		System.out.println("接受到服务器响应");
		byte[] dst = new byte[byteBuf.readableBytes()];
		StringBuilder sb = new StringBuilder();
		while (byteBuf.readableBytes() != 0) {
			byteBuf.readBytes(dst);
			String temp = new String(dst);
			sb.append(temp);
		}
		System.out.println(sb);
		ctx.fireChannelRead(sb.toString());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable e)
			throws Exception {
		e.printStackTrace();
		System.out.println("发生异常,关闭通道");
		ctx.close();
	}
	
	
	

}
