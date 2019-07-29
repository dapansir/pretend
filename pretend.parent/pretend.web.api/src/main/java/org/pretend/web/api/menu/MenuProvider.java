package org.pretend.web.api.menu;

import java.util.List;

import org.pretend.web.api.entiy.MenuBar;

public interface MenuProvider {

	List<MenuBar> getMenubars(int power);
}
