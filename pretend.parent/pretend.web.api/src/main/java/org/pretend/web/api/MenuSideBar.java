package org.pretend.web.api;

import java.util.List;

import org.pretend.web.api.entiy.Bar;

public class MenuSideBar {
	
	
	private String label;
	
	private List<Bar> childBar;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Bar> getChildBar() {
		return childBar;
	}

	public void setChildBar(List<Bar> childBar) {
		this.childBar = childBar;
	}
	
	

}
