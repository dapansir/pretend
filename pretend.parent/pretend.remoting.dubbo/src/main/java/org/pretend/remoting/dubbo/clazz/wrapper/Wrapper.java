package org.pretend.remoting.dubbo.clazz.wrapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

import org.jboss.netty.util.internal.ConcurrentHashMap;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol;
import com.alibaba.dubbo.rpc.proxy.InvokerInvocationHandler;

public class Wrapper {
	
	private static final Map<Class<?>,InvocationHandler> INVOCATIONES = new ConcurrentHashMap<Class<?>,InvocationHandler>();

	public static void makeWrapper(Class<?> clazz) {

		if (clazz.isPrimitive()) {
			throw new IllegalArgumentException("Can not create wrapper for primitive type: " + clazz);
		}
	}

	
	public static InvocationHandler getInvocationHandler(Class<?> serviceType,String urlString) throws Exception{
		Protocol protocol = new DubboProtocol();
		URL url = URL.valueOf(urlString);
		Invoker<?> handler = protocol.refer(serviceType, url);
		InvocationHandler invocation = new InvokerInvocationHandler(handler);
		return invocation;
	}
	
	public static InvocationHandler registryHandler(Class<?> serviceType,String url){
		InvocationHandler handler = null;
        try {
	        handler = INVOCATIONES.get(serviceType);
			if(null == handler){
				handler = getInvocationHandler(serviceType, url);
				INVOCATIONES.put(serviceType, handler);
			}
        } catch (Exception e) {
	        e.printStackTrace();
        }
		
		return handler;
	}
	
	public static Object invoke(String className,String methodName,Class<?>[] parameterTypes,String[] args){
		Object result = null;
        try {
        	Class<?> clazz = Class.forName(className);
	        Method method = clazz.getMethod(methodName, parameterTypes);
	        InvocationHandler handler = registryHandler(clazz, null);
	        handler.invoke(null, method, args);
        } catch (ClassNotFoundException e) {
	        e.printStackTrace();
        } catch (SecurityException e) {
	        e.printStackTrace();
        } catch (NoSuchMethodException e) {
	        e.printStackTrace();
        } catch (Throwable e) {
	        e.printStackTrace();
        }
		return result;
	}
	
	
	
}
