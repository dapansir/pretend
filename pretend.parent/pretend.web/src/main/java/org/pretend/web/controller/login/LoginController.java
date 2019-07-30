package org.pretend.web.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	
	@RequestMapping("welcome")
	public String welcome() {
		
		return "index";
	}
}
