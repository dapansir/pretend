package org.pretend.remoting.dubbo.Helper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.pretend.common.Parameter;
import org.pretend.common.util.ObjectUtil;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.proxy.InvokerInvocationHandler;

public class InvokeHelper {
	
	private static final Protocol refprotocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
	
	private static final Map<String,InvocationHandler> INVOCATIONES = new ConcurrentHashMap<String,InvocationHandler>();
	
	
	private Class<?> clazz;
	
	private URL url;
	
	private String protocol;
	
	private String address;
	
	private Parameter parameters = new Parameter();
	
	private InvocationHandler handler;
	
	
	public InvokeHelper(String className, String protocol, String address) throws IllegalArgumentException, ClassNotFoundException {
	    super();
	    setClassName(className);
	    ObjectUtil.notNull(address, "地址不能为空!");
	    this.protocol = protocol;
	    this.address = address;
    }

	public void setClassName(String className)throws IllegalArgumentException, ClassNotFoundException{
		ObjectUtil.notNull(className, "类名不能为空!");
		clazz = Class.forName(className);
	}
	
	public URL toUrl(){
		if(null == url){
			url = URL.valueOf(protocol+"://"+address+"/"+clazz.getName());
			Map<String, String> map = parameters.getUnModifyableMap();
			if(null != map && !map.isEmpty()){
				for (String key : map.keySet()) {
					url = url.addParameter(key, map.get(key));
                }
			}
		}
		return url;
	}
	
	public Parameter getParameters(){
		
		return parameters;
	}
	
	private InvocationHandler getInvocationHandler() throws Exception{
		
		Invoker<?> handler = refprotocol.refer(clazz, url);
		
		return new InvokerInvocationHandler(handler);
	}
	
	
	private void registryHandler(){
        try {
        	if(null == url){
        		this.toUrl();
        	}
	        handler = INVOCATIONES.get(url.toFullString());
			if(null == handler){
				handler = this.getInvocationHandler();
				INVOCATIONES.put(url.toFullString(), handler);
			}
        } catch (Exception e) {
	        e.printStackTrace();
        }
	}
	
	
	public Object invoke(String methodName,Class<?>[] parameterTypes,Object[] args){
		Object result = null;
        try {
	        Method method = clazz.getMethod(methodName, parameterTypes);
	        this.registryHandler();
	        result = handler.invoke(this, method, args);
        } catch (SecurityException e) {
	        e.printStackTrace();
        } catch (NoSuchMethodException e) {
	        e.printStackTrace();
        } catch (Throwable e) {
	        e.printStackTrace();
        }
		return result;
	}
	
	public void resloveReq(Map<String,Object> request) throws ClassNotFoundException{
		address = String.valueOf(request.get("address"));
		ObjectUtil.notNull(address, "调用地址不能为空");
		protocol = String.valueOf(request.get("protocol"));
		ObjectUtil.notNull(protocol, "调用协议不能为空");
		clazz = Class.forName(String.valueOf(request.get("interfaceName")));
		//方法名
		String methodName = String.valueOf(request.get("method"));
		ObjectUtil.notNull(methodName, "调用方法名不能为空");
		//方法参数类型
		String classes = String.valueOf(request.get("classes"));
		Class<?>[] parameterTypes = new Class<?>[0];
		if(null != classes && classes.length() > 0){
			String[] parameterTypeString = classes.split(",");
			parameterTypes = new Class<?>[parameterTypeString.length];
			for (int i = 0; i < parameterTypeString.length; i++) {
				parameterTypes[i] = Class.forName(parameterTypeString[i]);
            }
		}
		
	}
}
