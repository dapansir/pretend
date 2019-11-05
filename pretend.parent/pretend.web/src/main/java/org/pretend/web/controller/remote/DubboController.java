package org.pretend.web.controller.remote;

import java.util.Map;

import org.pretend.remoting.dubbo.Helper.InvokeHelper;
import org.pretend.tools.util.BoUtil;
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

	@RequestMapping(value="/getBoInfos",method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public String getBoInfos(@RequestBody String code){
		
		return JSONObject.toJSONString(BoUtil.getPropertyInfos(TestBo.class));
	}
	
	@RequestMapping(value="/doTest",method=RequestMethod.POST)
	@ResponseBody
	public Object doTest(@RequestBody Map<String, String> param) {
		Object result = null;
		String className = "";
		String protocol = "";
		String address = "";
		String methodName = "";
		String[] classTypes = {};
		Object[] args = {};
		try {
	        InvokeHelper helper = new InvokeHelper(className, protocol, address);
	        helper.invoke(methodName, null, args);
        } catch (IllegalArgumentException e) {
        	result = "缺少必要信息";
        } catch (ClassNotFoundException e) {
        	result = "接口配置不正确";
        }
		return JSONObject.toJSONString(result);
	}
	
}
