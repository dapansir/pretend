package org.pretend.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ClassHelper {

	
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
		String setter = "set"+toMethodsuffix(property);
		Method[] methods = clazz.getDeclaredMethods();
		Class<?> fieldType = getPropertyType(clazz, property);
		ObjectUtil.notNull(fieldType, "property "+property+" not exist!");
		String name;
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			name = method.getName();
			if(name.equals(setter)&& method.getModifiers() == Modifier.PUBLIC) {
				Class<?>[] parameterTypes = method.getParameterTypes();
				if(parameterTypes.length == 1 && parameterTypes[0].equals(fieldType)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static  boolean hasGetter(Class<?> clazz ,String property) {
		ObjectUtil.notNull(clazz, "clazz must not be null");
		ObjectUtil.notNull(property, "property must not be null");
		String getter = "get"+toMethodsuffix(property);
		Class<?> fieldType = getPropertyType(clazz, property);
		ObjectUtil.notNull(fieldType, "property "+property+" not exist!");
		Method[] methods = clazz.getDeclaredMethods();
		String name;
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			name = method.getName();
			if(name.equals(getter)) {
				Class<?> returnType = method.getReturnType();
				//返回值类型相同，并且没有参数
				if(fieldType == returnType && method.getParameterTypes().length == 0 && method.getModifiers() == Modifier.PUBLIC) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static Class<?> getPropertyType(Class<?> clazz ,String property){
		ObjectUtil.notNull(clazz, "clazz must not be null");
		ObjectUtil.notNull(property, "property must not be null");
		Field[] fields = clazz.getDeclaredFields();
		Class<?> fieldType = null;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if(field.getName().equals(property)) {
				fieldType = field.getType();
				break;
			}
		}
		return fieldType;
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
	
	
}
