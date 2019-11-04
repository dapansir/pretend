package org.pretend.tools.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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

}
