package org.pretend.web.controller.menu;

import org.pretend.web.api.MainSideBar;
import org.pretend.web.api.menu.MenuProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;


@RequestMapping("/menu")
@Controller
public class MenuController {

	@Autowired
	private MenuProvider menuProvider;
	
	@RequestMapping("/left")
	@ResponseBody
	public String leftBar(){
		MainSideBar mainSideBar = menuProvider.getMainSideBar(1);
		return JSON.toJSONString(mainSideBar);
	}
}
