package org.pretend.web.api;

import java.util.List;


public class MainSideBar {

	private String userPanel;
	
	private List<MenuSideBar> menuSideBar;

	public String getUserPanel() {
		return userPanel;
	}

	public void setUserPanel(String userPanel) {
		this.userPanel = userPanel;
	}

	public List<MenuSideBar> getMenuSideBar() {
		return menuSideBar;
	}

	public void setMenuSideBar(List<MenuSideBar> menuSideBar) {
		this.menuSideBar = menuSideBar;
	}
	
	
	
}
