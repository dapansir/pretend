package org.pretend.common.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.pretend.common.annotation.Activity;
import org.pretend.common.annotation.Adaptive;
import org.pretend.common.annotation.Name;
import org.pretend.common.util.ClassHelper;

public class ExtensionLoader<T> {
	//static
	private static final String DIRECTORY_INTERNAL = "META-INF/extension/internal/";
	
	private static final String DIRECTORY_SERVICE = "META-INF/extension/service/";
	
	private static final List<String> COMMENTS = new ArrayList<String>(Arrays.asList("#","//","--"));
	
	private static final Map<Class<?>,ExtensionLoader<?>> CACHED_LODERES = new ConcurrentHashMap<Class<?>,ExtensionLoader<?>>();
	
	private static final Map<Class<?>,Object> CACHED_EXTENSION_INSTANCES= new ConcurrentHashMap<Class<?>,Object>();
	
//	private static final LoggerFactory LOGGER_FACTORY = ExtensionLoader.getExtensionLoader(LoggerFactory.class).getActiveExtension();
	
//	private static final Logger LOGGER = LOGGER_FACTORY.getLogger(ExtensionLoader.class); 
	
	//Object
	private final Map<String,Holder<Object>> cachedHolders = new HashMap<String,Holder<Object>>();
	
	private Map<String,Class<?>> cachedClasses = new HashMap<String,Class<?>>();
	
	private Class<?> cachedActiveClass;
	
	private Class<?> cachedAdaptiveClass;
	
	private Class<?> type;
	
	private String defaultName;
	
	private Set<Class<?>> cachedWarpperClass = new HashSet<Class<?>>();
	
	public ExtensionLoader(Class<?> type){
		this.type = type;
	}
	
	private void logger(String msg,Throwable e){
		if(loggerEnable()){
//			LOGGER.info(msg,e);
		}
	}
	private boolean loggerEnable(){
//		return LOGGER != null;
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public T getExtension(String name){
		Holder<Object> holder = cachedHolders.get(name);
		if(null == holder){
			cachedHolders.put(name, new Holder<Object>());
			holder = cachedHolders.get(name);
		}
		if(null == holder.getInstance()){
			Object instance = createExtension(name);
			holder.setInstance(instance);
		}
		return (T) holder.getInstance();
	}
	
	@SuppressWarnings("unchecked")
	private T createExtension(String name){
		Class<?> clazz = getExtensionClass().get(name);
		if(null == clazz){
			throw new IllegalStateException(type.getName()+"文件中不存在name为["+name+"]的定义;");
		}
		T instance = (T)CACHED_EXTENSION_INSTANCES.get(clazz);
		if (null == instance) {
			try {
				CACHED_EXTENSION_INSTANCES.put(clazz, clazz.newInstance());
				instance = (T) CACHED_EXTENSION_INSTANCES.get(clazz);
				injectExtension(instance);
				if (!cachedWarpperClass.isEmpty()) {
					for (Class<?> cla : cachedWarpperClass) {
						instance = (T) cla.getConstructor(type).newInstance();
					}
				}
			} catch (Throwable e) {
				logger("", e);
			}
		}
		return instance;
	}
	
	private T injectExtension(T instance){
		Method[] methods = instance.getClass().getMethods();
		for (Method method : methods) {
			if(Modifier.isPublic(method.getModifiers())
					&&method.getName().startsWith("set")
					&&method.getParameterTypes().length == 1){
				
			}
			
		}
		return instance;
	}
	
	public boolean hasExtension(String name){
		if(null == name || name.trim().equals("")){
			return false;
		}
		if(cachedClasses.isEmpty()){
			cachedClasses = getExtensionClass();
		}
		return cachedClasses.get(name)!= null;
	}

	private Map<String,Class<?>> getExtensionClass(){
		Map<String,Class<?>> extensionClasses = new HashMap<String, Class<?>>();
		loadDirectory(extensionClasses, DIRECTORY_INTERNAL, type.getName());
		loadDirectory(extensionClasses, DIRECTORY_SERVICE, type.getName());
		return extensionClasses;
	}
	
	@SuppressWarnings("unchecked")
	public T getActiveExtension(){
		if(cachedActiveClass == null){
			getExtensionClass();
		}
		if(cachedActiveClass == null){
			throw new IllegalStateException("没有找到类型为:"+type.getName()+"且有@Activity注解的类");
		}
		T instance = null;
		try {
			instance = (T) cachedActiveClass.newInstance();
		} catch (InstantiationException e) {
			logger("", e);
		} catch (IllegalAccessException e) {
			logger("", e);
		}
		return instance;
	}
	
	
	@SuppressWarnings("unchecked")
	public T getAdaptiveExentsion(){
		T instance = null;
		if(cachedAdaptiveClass == null){
			getExtensionClass();
		}
		if(cachedAdaptiveClass == null){
			throw new IllegalStateException("没有找到类型为:"+type.getName()+"且有@Adaptive注解的类");
		}
		try {
			instance = (T) cachedAdaptiveClass.newInstance();
		} catch (InstantiationException e) {
			logger("", e);
		} catch (IllegalAccessException e) {
			logger("", e);
		}
		return instance;
	}
	
	
	private void loadDirectory(Map<String,Class<?>> extensionClasses,String dir,String type){
		String files = dir + type;
		ClassLoader classLoader = ClassHelper.getClassLoader(ExtensionLoader.class);
		if(classLoader != null){
			try {
				Enumeration<URL> resources = classLoader.getResources(files);
				while (resources.hasMoreElements()) {
					URL url = (URL) resources.nextElement();
					logger("加载资源:"+url, null);
					loadResource(extensionClasses, url, classLoader);;
				}
			} catch (IOException e) {
				logger("", e);
			}
		}
	}
	
	private void loadResource(Map<String,Class<?>> extensionClasses,URL url,ClassLoader classLoader){
		Name defaultAnnotation = type.getAnnotation(Name.class);
		if(null != defaultAnnotation){
			String value = defaultAnnotation.value();
			if(null != value && value.trim().length() > 0){
				String[] names = value.split(",");
				if(names.length > 1){
					throw new IllegalStateException("org.pretend.common.loader.Name 注解只能配置一个默认名称");
				}
				defaultName = names[0];
			}
		}
		InputStream is =  null;
		BufferedReader reader = null;
		try {
			is = url.openStream();
			reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			String line = reader.readLine();
			String prefix = null;
			String name = null;
			String clazz = null;
			while(null != line && line.trim().length()>0){
				line = line.trim();
				prefix = line.substring(0,1);
				if(COMMENTS.contains(prefix)){
					logger("注释:"+line, null);
					line = reader.readLine();
					continue;
				}
				String[] split = line.split("=");
				if(split.length>1){
					name = split[0];
					clazz = split[1];
				}else{
					name = defaultName;
					clazz = split[0];
				}
				loadClass(extensionClasses,url,Class.forName(clazz,true,classLoader), name);
				line = reader.readLine();
			}
		} catch (IOException e) {
			logger("", e);
		} catch (ClassNotFoundException e) {
			logger("", e);
		} catch (IllegalAccessException e) {
			logger("", e);
		} catch (NoSuchMethodException e) {
			logger("", e);
		} catch (SecurityException e) {
			logger("", e);
		}finally{
			try {
				is.close();
				reader.close();
			} catch (IOException e) {
				logger("关闭文件流失败", e);
			}
		}
	}
	
	private void loadClass(Map<String,Class<?>> extensionClasses,URL url,Class<?> clazz,String name) throws IllegalAccessException, NoSuchMethodException, SecurityException{
		if(!type.isAssignableFrom(clazz)){
			throw new IllegalAccessException("配置文件"+url.toString()+"配置的类不是"+type.getName()+"的实现类!");
		}
		clazz.getConstructor();
		if(isWarpperClass(clazz)){
			cachedWarpperClass.add(clazz);
		}
		//取最后一个有Activity注解的类
		Activity activity = clazz.getAnnotation(Activity.class);
		if(null != activity){
			cachedActiveClass = clazz;
		}
		Adaptive adaptive = clazz.getAnnotation(Adaptive.class);
		if (null != adaptive) {
			cachedAdaptiveClass = clazz;
		}
		extensionClasses.put(name,clazz);
	}
	
	private boolean isWarpperClass(Class<?> clazz){
		boolean isWarpper = false;
		try {
			clazz.getConstructor(type);
			isWarpper = true;
		} catch (NoSuchMethodException e) {
			logger("没有参数为type的构造函数", e);
		} catch (SecurityException e) {
			logger("系统错误", e);
		}
		return isWarpper;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static <T>  ExtensionLoader<T> getExtensionLoader(Class<T> type){
		
		if(null == type){
			throw new IllegalArgumentException("ExtensionType can not be null!");
		}
		
		if(!type.isInterface()){
			throw new IllegalArgumentException("Argument "+type.getName()+" is not an interface!");
		}
		
		ExtensionLoader<T> loader = (ExtensionLoader<T>) CACHED_LODERES.get(type);
		
		if(null == loader){
			loader = new ExtensionLoader<T>(type);
			CACHED_LODERES.put(type, loader);
		}
		
		return loader;
	}

}
