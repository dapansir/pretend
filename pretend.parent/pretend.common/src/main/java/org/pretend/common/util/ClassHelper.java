package org.pretend.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.pretend.common.MethodDescription;
import org.pretend.common.constant.MethodConstants;

public class ClassHelper {

	private static final List<Class<?>> PRIMITIVE_TYPES = new ArrayList<Class<?>>();
	
	private static final List<Class<?>> WRAPPERED_TYPES = new ArrayList<Class<?>>();

	static{
		PRIMITIVE_TYPES.add(byte.class);
		WRAPPERED_TYPES.add(Byte.class);
		PRIMITIVE_TYPES.add(short.class);
		WRAPPERED_TYPES.add(Short.class);
		PRIMITIVE_TYPES.add(int.class);
		WRAPPERED_TYPES.add(Integer.class);
		PRIMITIVE_TYPES.add(double.class);
		WRAPPERED_TYPES.add(Double.class);
		PRIMITIVE_TYPES.add(float.class);
		WRAPPERED_TYPES.add(Float.class);
		PRIMITIVE_TYPES.add(long.class);
		WRAPPERED_TYPES.add(Long.class);
		WRAPPERED_TYPES.add(BigDecimal.class);
	}
	
	private ClassHelper(){
		
	}
	
	public static ClassLoader getClassLoader(Class<?> type){
		ClassLoader classLoader = null;
		try {
			classLoader = Thread.currentThread().getContextClassLoader();
		} catch (Exception e) {
			try {
				classLoader = type.getClassLoader();
			} catch (Exception e2) {
				try {
					classLoader = ClassLoader.getSystemClassLoader();
				} catch (Exception e3) {
					//没办法了
				}
			}
		}
		return classLoader;
	}
	
	
	public static boolean hasSetter(Class<?> clazz,String property) {
		ObjectUtil.notNull(clazz, "clazz must not be null");
		ObjectUtil.notNull(property, "property must not be null");
		Method[] methods = clazz.getDeclaredMethods();
		Class<?> fieldType = getPropertyType(clazz, property);
		String setter = getMethodPrefix(fieldType, MethodConstants.METHOD_TYPE_SET)+toMethodsuffix(property);
		ObjectUtil.notNull(fieldType, "property "+property+" not exist!");
		String name;
		boolean find = false;
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			name = method.getName();
			if(name.equals(setter)&& method.getModifiers() == Modifier.PUBLIC) {
				Class<?>[] parameterTypes = method.getParameterTypes();
				if(parameterTypes.length == 1 && parameterTypes[0].equals(fieldType)) {
					find = true;
					return true;
				}
			}
		}
		if (!find && !javaSupperClass(clazz.getSuperclass())) {
			return hasSetter(clazz.getSuperclass(), property);
		}
		return false;
	}
	
	public static  boolean hasGetter(Class<?> clazz ,String property) {
		ObjectUtil.notNull(clazz, "clazz must not be null");
		Class<?> fieldType = getPropertyType(clazz, property);
		String getter = getMethodPrefix(fieldType, MethodConstants.METHOD_TYPE_GET)+toMethodsuffix(property);
		ObjectUtil.notNull(fieldType, "property "+property+" not exist!");
		Method[] methods = clazz.getDeclaredMethods();
		String name;
		boolean find = false;
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			name = method.getName();
			if(name.equals(getter)) {
				Class<?> returnType = method.getReturnType();
				//返回值类型相同，并且没有参数
				if(fieldType == returnType && method.getParameterTypes().length == 0 && method.getModifiers() == Modifier.PUBLIC) {
					find = true;
					return true;
				}
			}
		}
		if (!find && !javaSupperClass(clazz.getSuperclass())) {
			return hasSetter(clazz.getSuperclass(), property);
		}
		return false;
	}
	
	public static Class<?> getPropertyType(Class<?> clazz ,String property){
		ObjectUtil.notNull(clazz, "clazz must not be null");
		ObjectUtil.notNull(property, "property must not be null");
		Field[] fields = clazz.getDeclaredFields();
		Class<?> fieldType = null;
		boolean find = false;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if(field.getName().equals(property)) {
				fieldType = field.getType();
				find = true;
				break;
			}
		}
		if (!find && !javaSupperClass(clazz.getSuperclass())) {
			return getPropertyType(clazz.getSuperclass(), property);
		}
		return fieldType;
	}
	
	public static boolean javaSupperClass(Class<?> clazz){
		ObjectUtil.notNull(clazz, "cannot be null!");
		if (clazz.equals(Object.class)) {
			return true;
		}
		return false;
	}
	
	private static String getMethodPrefix(Class<?> fieldType,String methodType){
		ObjectUtil.notNull(fieldType, "属性类型不能为空");
		ObjectUtil.notNull(methodType, "方法类型不能为空");
		if(MethodConstants.METHOD_TYPE_SET.equals(methodType)){
			if(fieldType.equals(boolean.class) || fieldType.equals(Boolean.class)){
				return MethodConstants.METHOD_TYPE_SET_BOOLEAN_PREFIX;
			}
			return MethodConstants.METHOD_TYPE_SET_PREFIX;
		}
		return MethodConstants.METHOD_TYPE_GET_PREFIX;
	}
	
	/**
	 * such as "persono_name" to "PersonName"
	 * @param string
	 * @return
	 */
	public static  String toMethodsuffix(String string) {
		ObjectUtil.notNull(string, "clazz must not be null");
		String temp = string; 
		if(string.length()>1) {
			 temp = temp.substring(0,1).toUpperCase()+temp.substring(1);
		}
		return temp;
	}
	
	public static Method getGetter(Class<?> clazz ,String property){
		
		
		return null;
	}
	
	public static Method getGetterMethod(Class<?> clazz ,String property) {
		ObjectUtil.notNull(clazz, "clazz must not be null");
		ObjectUtil.notNull(property, "property must not be null");
		Class<?> propertyType = getPropertyType(clazz, property);
		Method m = null;
		String methodName = null;
		if(null != propertyType) {
			String getter = getMethodPrefix(propertyType, MethodConstants.METHOD_TYPE_GET)+toMethodsuffix(property);
			Method[] declaredMethods = clazz.getDeclaredMethods();
			//先在该类声明的方法中查找get方法
			if(null != declaredMethods && declaredMethods.length > 0) {
				for (int i = 0; i < declaredMethods.length; i++) {
					Method method = declaredMethods[i];
					methodName = method.getName();
					Class<?>[] parameterTypes = method.getParameterTypes();
					//公用,没有参数,返回值和属性相同,并且符合getXxx()命名的才是get方法
					if(null != parameterTypes 
							&& parameterTypes.length == 0 
							&& method.getModifiers() == Modifier.PUBLIC 
							&& method.getReturnType().equals(propertyType) 
							&& methodName.equals(getter))  {
						m = method; 
						break;
					}
				}
			}
			//如果没有找到,就去父类里面找
			if(null == m){
				Class<?> superclass = clazz.getSuperclass();
				if(!javaSupperClass(superclass)){
					m = getGetterMethod(superclass, property);
				}
			}
		}
		return m;
	}
	
    public static boolean isString(Class<?> clazz) {
		if(clazz.equals(String.class)){
			return true;
		}
	    return false;
    }

    public static boolean isNumber(Class<?> clazz) {
	    if(isString(clazz)){
	    	return false;
	    }
	    if(isBoolean(clazz)){
	    	return false;
	    }
	    if(PRIMITIVE_TYPES.contains(clazz)){
	    	return true;
	    }
	    if(WRAPPERED_TYPES.contains(clazz)){
	    	return true;
	    }
	    return false;
    }

    public static boolean isBoolean(Class<?> clazz) {
		if(clazz.equals(boolean.class) || clazz.equals(Boolean.class)){
			return true;
		}
	    return false;
    }
    
    public static List<MethodDescription> getShortMthodName(Class<?> clazz){
    	
    	List<MethodDescription> names = new ArrayList<MethodDescription>();
    	Method[] methodes = clazz.getDeclaredMethods();
    	for (int i = 0; i < methodes.length; i++) {
    		Method method = methodes[i];
    		int modifier = Modifier.PUBLIC+Modifier.ABSTRACT;
    		if(method.getModifiers() == Modifier.PUBLIC || method.getModifiers() == modifier ){
    			MethodDescription desc = new MethodDescription();
    			desc.setBelongTo(clazz.getName());
    			desc.setSimpleReturnType(method.getReturnType().getSimpleName());
    			desc.setReturnType(method.getReturnType().getName());
    			desc.setName(method.getName());
    			desc.setParameterCount(method.getParameterTypes().length);
    			Class<?>[] parameterTypes = method.getParameterTypes();
    			if(parameterTypes.length > 0){
    				String[] parameterTypesStr = new String[parameterTypes.length];
        			String[] simpleParameterTypes = new String[parameterTypes.length];
        			for (int j = 0; j < parameterTypes.length; j++) {
        				parameterTypesStr[j] = parameterTypes[j].getName();
        				simpleParameterTypes[j] = parameterTypes[j].getSimpleName();
                    }
        			desc.setParameterTypes(parameterTypesStr);
        			desc.setSimpleParameterTypes(simpleParameterTypes);
    			}
    			desc.setDescription(null);
    			names.add(desc);
    		}
        }
		return names;
    }
    
}
