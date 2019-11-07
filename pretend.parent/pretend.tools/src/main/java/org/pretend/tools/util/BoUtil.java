package org.pretend.tools.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pretend.common.MethodDescription;
import org.pretend.common.util.ClassHelper;
import org.pretend.common.util.ObjectUtil;
import org.pretend.tools.PropertyInfo;

public class BoUtil {
	
	public static List<PropertyInfo> getPropertyInfos(Class<?> clazz){
		ObjectUtil.notNull(clazz, "class can not be null!");
		List<PropertyInfo> arrayList = new ArrayList<PropertyInfo>();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			PropertyInfo info = new PropertyInfo();
			Field field = fields[i];
			int modifiers = field.getModifiers();
			if(modifiers == Modifier.STATIC 
					|| modifiers == Modifier.FINAL
					|| modifiers == Modifier.FINAL+Modifier.STATIC
					|| modifiers == Modifier.FINAL+Modifier.STATIC+Modifier.PRIVATE
					|| modifiers == Modifier.FINAL+Modifier.STATIC+Modifier.PUBLIC
					|| modifiers == Modifier.FINAL+Modifier.STATIC+Modifier.PROTECTED){
				continue;
			}
			info.setFieldType(getFieldType(field.getType()));
			info.setFieldName(field.getName());
			arrayList.add(info);
        }
		return arrayList;
	}
	
	private static int getFieldType(Class<?> fieldClazz){
		if(ClassHelper.isNumber(fieldClazz)){
			return 1;
		}
		if(ClassHelper.isBoolean(fieldClazz)){
			return 2;
		}
		if(ClassHelper.isStringType(fieldClazz)){
			return 3;
		}
		if(ClassHelper.isTime(fieldClazz)){
			return 4;
		}
		return 0;
	}
	
	public static Map<String, Object> getMethodDetail(MethodDescription desc) {
		Map<String, Object> result = new HashMap<String, Object>();
		String[] parameterTypes = desc.getParameterTypes();
		if (parameterTypes != null) {
			for (int i = 0; i < parameterTypes.length; i++) {
				try {
	                Class<?> clazz = Class.forName(parameterTypes[i]);
	                String index = String.valueOf(i+1);
	                if(clazz.isPrimitive() || ClassHelper.isStringType(clazz)){
	                	PropertyInfo info = new PropertyInfo();
	                	info.setFieldName("arg");
	                	info.setFieldType(getFieldType(clazz));
	                	result.put(index, info);
	                }else{
	                	result.put(index, getPropertyInfos(clazz));
	                }
                } catch (ClassNotFoundException e) {
	                e.printStackTrace();
                }
			}
		}
		
		return result;
	}

}
