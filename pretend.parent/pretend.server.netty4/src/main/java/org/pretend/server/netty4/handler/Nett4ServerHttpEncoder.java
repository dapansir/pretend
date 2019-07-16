package org.pretend.server.netty4.handler;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.util.CharsetUtil;

import org.pretend.server.netty4.element.Html;
import org.pretend.server.netty4.element.HtmlBody;
import org.pretend.server.netty4.element.HtmlHead;
import org.pretend.server.netty4.element.HtmlMeta;
import org.pretend.server.netty4.element.HtmlTitle;
import org.pretend.server.netty4.util.HtmlUtil;

public class Nett4ServerHttpEncoder extends ChannelOutboundHandlerAdapter {
	
	

	@Override
	public void write(ChannelHandlerContext ctx, Object msg,
			ChannelPromise promise) throws Exception {
		DefaultFullHttpResponse response = null;
		if(msg instanceof DefaultFullHttpResponse){
			response = (DefaultFullHttpResponse)msg;
			ByteBuf content = response.content();
			content.writeBytes(getHtml().getBytes(CharsetUtil.UTF_8));
			response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;"+CharsetUtil.UTF_8);
		}
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}
	
	private String getHtml(){
		Html html = new Html();
		HtmlHead head = new HtmlHead();
		HtmlMeta meta = new HtmlMeta();
		HtmlBody body = new HtmlBody();
		HtmlTitle title = new HtmlTitle();
		//set head
		html.setHead(head);
		//set body
		html.setBody(body);
		//set meta-info
		meta.setHttp_equiv(HttpHeaderNames.CONTENT_TYPE.toString());
		meta.setContent("text/html;"+CharsetUtil.UTF_8);
		List<HtmlMeta> metaes = new ArrayList<HtmlMeta>();
		metaes.add(meta);
		head.setMetaes(metaes);
		//set title
		title.setTitle("test");
		head.setTitle(title);
		//set body element 
		body.addElement("<a href='https://www.baidu.com'>打开百度</a>");
		return HtmlUtil.build(html);
	}
}
