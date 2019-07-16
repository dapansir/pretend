package org.pretend.server.netty4.element;

import org.pretend.server.netty4.element.api.HeadElement;
import org.pretend.server.netty4.element.api.NullElement;

public class HtmlBase implements HeadElement,NullElement {
	private String href;
	
	private String target;

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public String toString() {
		
		return "<base href=\""+href+"\" />\n<base target=\""+target+"\" />";
	}

	@Override
	public boolean isNull() {
		return null == href || "".equals(href.trim())||null==target||"".equals(target.trim()) ;
	}
	
	
	
}
