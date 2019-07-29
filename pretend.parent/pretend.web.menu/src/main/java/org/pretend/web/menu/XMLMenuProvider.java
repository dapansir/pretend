package org.pretend.web.menu;

import java.util.List;

import org.pretend.common.bean.BeanDefinition;
import org.pretend.tools.parse.document.DocumentParser;
import org.pretend.web.api.entiy.MenuBar;
import org.pretend.web.api.menu.MenuProvider;

public class XMLMenuProvider implements MenuProvider {
	
	private static final String CONFIG_PATH = "META-INF/config/pretend-menu-1.0.0.xsd";

	@Override
	public List<MenuBar> getMenubars(int power) {
		DocumentParser parse = new DocumentParser(CONFIG_PATH);
		try {
			List<BeanDefinition> list = parse.parseXML();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
