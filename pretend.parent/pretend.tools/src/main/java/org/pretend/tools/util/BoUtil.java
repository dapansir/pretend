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
			
			if(ClassHelper.isNumber(field.getType())){
				info.setFieldType(1);
			}else if(ClassHelper.isBoolean(field.getType())){
				info.setFieldType(2);
			}else if(ClassHelper.isString(field.getType())){
				info.setFieldType(3);
			}else{
				info.setFieldType(4);//其他类型,不支持
			}
			info.setFieldName(field.getName());
			arrayList.add(info);
        }
		return arrayList;
	}
	
	public static Map<String, Object> getMethodDetail(MethodDescription desc) {
		Map<String, Object> result = new HashMap<String, Object>();
		String[] parameterTypes = desc.getParameterTypes();
		if (parameterTypes != null) {
			for (int i = 0; i < parameterTypes.length; i++) {
				try {
	                Class<?> clazz = Class.forName(parameterTypes[i]);
	                int index = i+1;
	                if(clazz.isPrimitive() || ClassHelper.isStringType(clazz)){
	                	result.put(String.valueOf("parameter"+index), "arg"+index);
	                }else{
	                	result.put(String.valueOf("parameter"+index), getPropertyInfos(clazz));
	                }
                } catch (ClassNotFoundException e) {
	                e.printStackTrace();
                }
			}
		}
		
		return result;
	}

}
