package org.pretend.web.menu.xml;

import java.util.ArrayList;
import java.util.List;

import org.pretend.common.bean.BeanDefinition;
import org.pretend.tools.parse.document.DocumentParser;
import org.pretend.web.api.MainSideBar;
import org.pretend.web.api.entiy.Bar;
import org.pretend.web.api.menu.MenuProvider;

public class XMLMenuProvider implements MenuProvider {
	
	private static final String CONFIG_PATH = "META-INF/config/menu.xsd";

	@Override
	public List<Bar> getMenubars(int power) {
		List<Bar> bares = null;
		DocumentParser parse = new DocumentParser(CONFIG_PATH);
		List<BeanDefinition> beanDefinitiones = null;
		try {
			beanDefinitiones = parse.parseXML();
			if(null != beanDefinitiones && !beanDefinitiones.isEmpty()){
				bares = new ArrayList<Bar>();
				for (BeanDefinition beanDefinition : beanDefinitiones) {
					Bar bar = (Bar) beanDefinition.getSource();
					bares.add(bar);
	            }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bares;
	}

	@Override
    public MainSideBar getMainSideBar(int power) {
		DocumentParser parse = new DocumentParser(CONFIG_PATH);
		MainSideBar mainSideBar = null;
		try {
	        List<BeanDefinition> definitions = parse.parseXML();
	        if(null != definitions && !definitions.isEmpty()){
	        	mainSideBar = (MainSideBar) definitions.get(0).getSource();
	        }
        } catch (Exception e) {
	        e.printStackTrace();
        }
	    return mainSideBar;
    }

}
