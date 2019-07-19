package org.pretend.server.netty4.handler.rpc;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class InternalDecoder extends ChannelInboundHandlerAdapter{

	private ByteBuf byteBuf;
	
	
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		byteBuf = ctx.alloc().buffer(4);
		super.channelActive(ctx);
	}



	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		try {
			StringBuilder sb = new StringBuilder();
			byteBuf = (ByteBuf) msg;
			byte[] header = new byte[byteBuf.readableBytes()];
			while (byteBuf.isReadable()) {
//				byteBuf.readBytes(header);
				byte readByte = byteBuf.readByte();
				sb.append((char)readByte);
				if(readByte == 28){
					ctx.close();
				}
			}
			System.out.println(sb);
			System.out.flush();
			ByteBuf res = ctx.alloc().buffer(12);
			res.writeBytes("aaaa".getBytes());
			ctx.writeAndFlush(res);
		}finally{
			ReferenceCountUtil.release(msg);
		}
	}
	
	
}
