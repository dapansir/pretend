package org.pretend.server.netty4.util;

import org.pretend.server.netty4.element.Html;

public class HtmlUtil {

//	private static final ElementAccesser accesser = ExtensionLoader.getExtensionLoader(ElementAccesser.class).getActiveExtension();
	
	public static String build(Html html){
		StringBuilder htmlContent = new StringBuilder();
		htmlContent.append("<html>\n");
		htmlContent.append(html.getHead()== null?"":html.getHead());
		htmlContent.append(html.getCss()== null?"":html.getCss());
		htmlContent.append(html.getJavaScript()== null?"":html.getJavaScript());
		htmlContent.append(html.getBody()== null?"":html.getBody());
		htmlContent.append("</html>");
		return htmlContent.toString();
	}
	
}
