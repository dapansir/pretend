package org.pretend.server.netty4.element;

import org.pretend.server.netty4.element.api.HeadElement;
import org.pretend.server.netty4.element.api.NullElement;
import org.pretend.server.netty4.util.StringUitl;

public class HtmlTitle implements HeadElement,NullElement {

	private String innerHtml;
	
	
	public String getTitle() {
		return innerHtml;
	}

	public void setTitle(String title) {
		this.innerHtml = title;
	}



	@Override
	public String toString() {
		return "<title>"+(innerHtml==null?"":innerHtml)+"</title>";
	}

	@Override
	public boolean isNull() {
		return StringUitl.isEmpty(innerHtml);
	}

	
	
}
