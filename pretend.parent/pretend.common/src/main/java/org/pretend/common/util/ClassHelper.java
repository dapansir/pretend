package org.pretend.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.pretend.common.constant.MethodConstants;

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
	
	private static boolean javaSupperClass(Class<?> clazz){
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
	
	
}
