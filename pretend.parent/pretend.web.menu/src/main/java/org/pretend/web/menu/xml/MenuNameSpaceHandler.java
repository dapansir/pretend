package org.pretend.web.menu.xml;


import org.pretend.tools.namespace.AbstractNameSpaceHandler;
import org.pretend.tools.parse.element.ElementParser;
import org.pretend.web.api.MainSideBar;
import org.pretend.web.api.MenuSideBar;
import org.pretend.web.api.entiy.Bar;
import org.pretend.web.menu.parser.MenuParser;

public class MenuNameSpaceHandler extends AbstractNameSpaceHandler{
	
	static{
		registerParser("bar", new MenuParser(Bar.class));
		registerParser("menuSideBar", new MenuParser(MenuSideBar.class));
		registerParser("mainSideBar", new MenuParser(MainSideBar.class));
	}

}
