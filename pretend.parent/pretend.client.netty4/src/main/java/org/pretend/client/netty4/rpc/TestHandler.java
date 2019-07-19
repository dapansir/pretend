package org.pretend.client.netty4.rpc;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TestHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		TestBo bo = new TestBo();
		ctx.write(bo);
		ctx.flush();
	}

	
	
}
