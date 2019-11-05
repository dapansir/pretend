package org.pretend.web.controller.remote;

import java.util.List;
import java.util.Map;

import org.pretend.common.MethodDescription;
import org.pretend.common.util.ClassHelper;
import org.pretend.remoting.dubbo.Helper.InvokeHelper;
import org.pretend.tools.util.BoUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value="/getMethodes",method=RequestMethod.GET)
	@ResponseBody
	public String getMethodes(@RequestParam String interface_name){
		if(null == interface_name ||  interface_name.length() == 0 || "null".equals(interface_name)){
			return null;
		}
		Class<?> clazz = null;
		try {
			clazz = Class.forName(interface_name);
        } catch (ClassNotFoundException e) {
        	return null;
        }
		List<MethodDescription> list = ClassHelper.getShortMthodName(clazz);
		return JSONObject.toJSONString(list);
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
