package org.pretend.web.controller.remote;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;


@Controller
@RequestMapping("/dubbo")
public class DubboController {
	
	
	@RequestMapping(value="/to_detail",method=RequestMethod.GET)
	public String get(){
		
		return "pages/invoke/invoke_detail";
	}

	@RequestMapping(value="/getBoInfos",method=RequestMethod.POST)
	@ResponseBody
	public String getBoInfos(@RequestBody String code){
		
		return JSONObject.toJSONString(null);
	}
	
	@RequestMapping(value="/doTest",method=RequestMethod.POST)
	@ResponseBody
	public Object doTest(Map<String, String> param) {
		Object result = null;
		
		return JSONObject.toJSONString(result);
	}
	
}
