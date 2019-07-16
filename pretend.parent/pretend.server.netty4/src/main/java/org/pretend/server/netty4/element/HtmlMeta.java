package org.pretend.server.netty4.element;

import org.pretend.server.netty4.element.api.HeadElement;
import org.pretend.server.netty4.element.api.NullElement;
import org.pretend.server.netty4.util.StringUitl;

public class HtmlMeta implements HeadElement,NullElement {
	
	private String name;
	
	private String content;
	
	private String http_equiv;
	
	private String scheme;

	
	public String getHttp_equiv() {
		return http_equiv;
	}

	public void setHttp_equiv(String http_equiv) {
		this.http_equiv = http_equiv;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<meta ");
		if(!StringUitl.isEmpty(name)){
			sb.append(" name=\"").append(name).append("\"");
		}else if(!StringUitl.isEmpty(http_equiv)){
			sb.append(" http-equiv=\"").append(http_equiv).append("\"");
		}else if(!StringUitl.isEmpty(scheme)){
			sb.append(" scheme=\"").append(scheme).append("\"");
		}
		sb.append(" content=\"").append(content).append("\"");
		sb.append("/>");
		return sb.toString();
	}

	@Override
	public boolean isNull() {
		return (StringUitl.isEmpty(name) 
				&& StringUitl.isEmpty(http_equiv) 
				&& StringUitl.isEmpty(scheme)) 
				|| StringUitl.isEmpty(content);
	}
	
}
