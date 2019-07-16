package org.pretend.server.netty4.element;

import java.util.ArrayList;
import java.util.List;

public class HtmlBody {
	
	private List<String> elements = new ArrayList<String>();

	@Override
	public String toString() {
		StringBuilder sb = new  StringBuilder();
		sb.append("<body>\n");
		if(!elements.isEmpty()){
			for (String string : elements) {
				sb.append(string).append("\n");
			}
		}
		sb.append("</body>\n");
		return sb.toString();
	} 
	
	public boolean addElement(String element){
		return elements.add(element);
	}
	
	
}
