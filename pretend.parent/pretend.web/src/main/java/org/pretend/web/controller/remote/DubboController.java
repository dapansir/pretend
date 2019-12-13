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
		List<MethodDescription> list = ClassHelper.getMethodesDes(clazz);
		return JSONObject.toJSONString(list);
	}
	
	@RequestMapping(value="/getMethodDetail",method=RequestMethod.POST)
	@ResponseBody
	public String getMethodDetail(@RequestBody Map<String,String> info){
		MethodDescription desc = new MethodDescription();
		desc.setBelongTo(info.get("belongTo"));
		desc.setName(info.get("name"));
		desc.setParameterCount(Integer.parseInt(info.get("parameterCount")));
		desc.setReturnType(info.get("returnType"));
		desc.setSimpleReturnType(info.get("simpleReturnType"));
		
		String parameterTypes = info.get("parameterTypes");
		if(null != parameterTypes && parameterTypes.length() > 0){
			desc.setParameterTypes(parameterTypes.split(","));
		}
		String simpleParameterTypes = info.get("simpleParameterTypes");
		if(null != simpleParameterTypes && simpleParameterTypes.length() > 0){
			desc.setSimpleParameterTypes(simpleParameterTypes.split(","));
		}
		desc.setDescription(null);
		Map<String, Object> methodDetail = BoUtil.getMethodDetail(desc);
		return JSONObject.toJSONString(methodDetail);
	}
	
	@RequestMapping(value="/doTest",method=RequestMethod.POST)
	@ResponseBody
	public Object doTest(@RequestBody Map<String, Object> param) {
		Object result = null;
		try {
			String jsonString = JSONObject.toJSONString(param);
			JSONObject jsonObject = JSONObject.parseObject(jsonString);
			String protocol = jsonObject.getString("protocol");
			String address = jsonObject.getString("address");
			String className = jsonObject.getString("interface_name");
			String methodName = jsonObject.getString("name");
			String parameterTypes = jsonObject.getString("parameter_types");
			String parameters = jsonObject.getString("parameters");
			//解析参数
			JSONObject parametersObject = JSONObject.parseObject(parameters);
			Class<?>[] argClasses = new Class<?>[0];
			Object[] args = new Object[0];
			if(null != parameterTypes && parameterTypes.length() > 0){
				String[] classes = parameterTypes.split(",");
				argClasses = new Class<?>[classes.length];
				args = new Object[classes.length];
				for (int i = 0; i < classes.length; i++) {
					argClasses[i] = Class.forName(classes[i]);
					args[i] = parametersObject.getObject(String.valueOf(i), argClasses[i]);
	           }
			}
			jsonObject.remove("protocol");
			jsonObject.remove("address");
			jsonObject.remove("interface_name");
			jsonObject.remove("name");
			jsonObject.remove("parameter_types");
			jsonObject.remove("parameters");
	        InvokeHelper helper = new InvokeHelper(className, protocol, address);
	        for (String key : jsonObject.keySet()) {
	        	helper.getParameters().addParameter(key, jsonObject.getString(key));
            }
	        result = helper.invoke(methodName, argClasses, args);
        } catch (IllegalArgumentException e) {
        	result = "缺少必要信息";
        } catch (ClassNotFoundException e) {
        	result = "接口配置不正确";
        }
		return JSONObject.toJSONString(result);
	}
	
}
