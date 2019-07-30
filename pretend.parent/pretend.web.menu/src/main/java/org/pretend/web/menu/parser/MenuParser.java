package org.pretend.web.menu.parser;

import java.util.ArrayList;
import java.util.List;

import org.pretend.common.bean.BeanDefinition;
import org.pretend.tools.parse.element.ElementParser;
import org.pretend.web.api.MainSideBar;
import org.pretend.web.api.MenuSideBar;
import org.pretend.web.api.entiy.Bar;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MenuParser extends ElementParser {

	public MenuParser(Class<?> clazz) {
	    super(clazz);
    }

	@Override
    public BeanDefinition parse(Element element) {
		
		if(super.getClazz().equals(MainSideBar.class)){
			return pasreMainSideBar(element);
		}
		if(super.getClazz().equals(MenuSideBar.class)){
			return pasreMenuSideBar(element);
		}
	    return super.parse(element);
    }
	
	private BeanDefinition pasreMainSideBar(Element element){
		MainSideBar source;
		BeanDefinition definition = null;
        try {
	        definition =  parseAttribute(element, MainSideBar.class);
	        source = (MainSideBar) definition.getSource();
	        NodeList childNodes = element.getChildNodes();
	        List<MenuSideBar> childBar = null;
	        if (childNodes.getLength() > 0) {
	        	childBar = new ArrayList<MenuSideBar>();
	            for (int i = 0; i < childNodes.getLength(); i++) {
	            	Node node = childNodes.item(i);
		            if(node.getNodeType() == Element.ELEMENT_NODE){
		            	MenuParser parser = new MenuParser(MenuSideBar.class);
		            	BeanDefinition menuSideBar = parser.pasreMenuSideBar((Element)node);
		            	childBar.add((MenuSideBar)menuSideBar.getSource());
		            }
	            }
	            source.setMenuSideBar(childBar);
            }
        } catch (Exception e) {
	        e.printStackTrace();
        }
		
		return definition;
	}
	
	private BeanDefinition pasreMenuSideBar(Element element){
		MenuSideBar source;
		BeanDefinition definition = null;
        try {
	        definition = parseAttribute(element,MenuSideBar.class);
	        source = (MenuSideBar) definition.getSource();
	        NodeList childNodes = element.getChildNodes();
	        List<Bar> childBar = null;
	        if(childNodes.getLength() > 0){
	        	ElementParser parser = new ElementParser(Bar.class);
	        	childBar = new ArrayList<Bar>();
	        	for (int i = 0; i < childNodes.getLength(); i++) {
		        	Node node = childNodes.item(i);
		            if(node.getNodeType() == Element.ELEMENT_NODE){
		            	BeanDefinition barBeanDefinition = parser.parse((Element)node);
		            	Bar bar  = (Bar) barBeanDefinition.getSource();
		            	childBar.add(bar);
		            }
	            }
	        	source.setChildBar(childBar);
	        }
	        
        } catch (Exception e) {
	        e.printStackTrace();
        }
		return definition;
	}

	
}
