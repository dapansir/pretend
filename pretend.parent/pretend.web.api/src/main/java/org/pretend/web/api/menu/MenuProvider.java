package org.pretend.web.api.menu;

import java.util.List;

import org.pretend.web.api.MainSideBar;
import org.pretend.web.api.entiy.Bar;

public interface MenuProvider {

	List<Bar> getMenubars(int power);
	
	
	MainSideBar getMainSideBar(int power);
}
