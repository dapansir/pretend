package org.pretend.server.netty4.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;

public class Nett4ServerHttpDecoder extends ChannelInboundHandlerAdapter {


	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		FullHttpResponse response = null;
		
		if(msg instanceof DefaultHttpRequest){
			DefaultHttpRequest request = (DefaultHttpRequest)msg;
			DecoderResult result = request.decoderResult();
			response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, getResponseStatu(result));
			ctx.write(response);
		}else if(msg instanceof LastHttpContent){
//			DefaultLastHttpContent content = (DefaultLastHttpContent)msg;
			System.out.println(msg);
			ctx.write(msg);
		}else{
			ctx.write(msg);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("服务端消息读取结束");
		ctx.flush();
		ctx.close();
	}
	
	private HttpResponseStatus getResponseStatu(DecoderResult result){
		if(result.isFailure()){
			return HttpResponseStatus.BAD_REQUEST;
		}
		return HttpResponseStatus.OK;
	}
	
	

}
