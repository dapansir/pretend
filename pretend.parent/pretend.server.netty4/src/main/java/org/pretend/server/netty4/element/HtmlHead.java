package org.pretend.server.netty4.element;

import java.lang.reflect.Field;
import java.util.List;

import org.pretend.server.netty4.element.api.HeadElement;
import org.pretend.server.netty4.element.api.NullElement;

public class HtmlHead implements HeadElement,NullElement  {

	private HtmlBase base;
	
	private List<HtmlCss> csses;
	
	private List<HtmlMeta> metaes;
	
	private List<HtmlLink> linkes;
	
	private List<JavaScript> jses;
	
	private HtmlTitle title;
	
	public HtmlBase getBase() {
		return base;
	}

	public void setBase(HtmlBase base) {
		this.base = base;
	}

	public List<HtmlCss> getCsses() {
		return csses;
	}

	public void setCsses(List<HtmlCss> csses) {
		this.csses = csses;
	}

	public List<HtmlMeta> getMetaes() {
		return metaes;
	}

	public void setMetaes(List<HtmlMeta> metaes) {
		this.metaes = metaes;
	}

	public List<HtmlLink> getLinkes() {
		return linkes;
	}

	public void setLinkes(List<HtmlLink> linkes) {
		this.linkes = linkes;
	}

	public List<JavaScript> getJses() {
		return jses;
	}

	public void setJses(List<JavaScript> jses) {
		this.jses = jses;
	}
	
	public HtmlTitle getTitle() {
		return title;
	}

	public void setTitle(HtmlTitle title) {
		this.title = title;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<head>\n");
		if(null != metaes){
			for (HtmlMeta meta : metaes) {
				sb.append(meta.toString()).append("\n");
			}
		}
		if(null != title && !title.isNull()){
			sb.append(title.toString()).append("\n");
		}
		if(null != linkes){
			for (HtmlLink link : linkes) {
				sb.append(link.toString()).append("\n");
			}
		}
		if(null != csses){
			for (HtmlCss htmlCss : csses) {
				sb.append(htmlCss.toString()).append("\n");
			}
		}
		if(null != jses){
			for (JavaScript js : jses) {
				sb.append(js.toString()).append("\n");
			}
		}
		sb.append("</head>\n");
		return sb.toString();
	}
	
	

	@Override
	public boolean isNull() {
		boolean result = false;
		Field[] fields = this.getClass().getFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if(field.getClass().isPrimitive()){
				
			}else{
				try {
					if(null != field.get(field)){
						result = true;
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
}
