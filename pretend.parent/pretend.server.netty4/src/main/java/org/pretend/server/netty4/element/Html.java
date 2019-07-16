package org.pretend.server.netty4.element;

public class Html {
	
	private HtmlBody body;
	
	private HtmlCss css;
	
	private HtmlHead head;
	
	private JavaScript javaScript;

	public HtmlBody getBody() {
		return body;
	}

	public void setBody(HtmlBody body) {
		this.body = body;
	}

	public HtmlCss getCss() {
		return css;
	}

	public void setCss(HtmlCss css) {
		this.css = css;
	}

	public HtmlHead getHead() {
		return head;
	}

	public void setHead(HtmlHead head) {
		this.head = head;
	}

	public JavaScript getJavaScript() {
		return javaScript;
	}

	public void setJavaScript(JavaScript javaScript) {
		this.javaScript = javaScript;
	}
	
}
