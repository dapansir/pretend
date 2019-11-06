package org.pretend.tools.entity;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.pretend.common.bean.BeanDefinition;
import org.pretend.common.bean.api.FieldAccessor;
import org.pretend.common.util.ObjectUtil;

public class PretendJson {
	
	private static final String QUOTATION = "\"";
	
	private static final String COLON = ":";
	
	private static final String COMMA = ",";
	
	private BeanDefinition bean;
	
	public PretendJson(BeanDefinition bean){
		this.bean = bean;
	}
	
	public static PretendJson toPretendJson(BeanDefinition bean){
		ObjectUtil.notNull(bean, "cannot be null!");
		
		return new PretendJson(bean);
	}
	
	private StringBuilder append(StringBuilder sb,FieldAccessor accessor){
		sb.append(QUOTATION).append(accessor.name()).append(QUOTATION).append(COLON);
		if(accessor.isNumber() || accessor.isBoolean()){
			sb.append(accessor.value());
		}
		if(accessor.isString()){
			sb.append(QUOTATION).append(accessor.value()).append(QUOTATION);
		}
		return sb;
	}
	
	public String toJson(){
		StringBuilder json = new StringBuilder("{");
		Map<String, Object> attributes = bean.getAttributes();
		if(!attributes.isEmpty()){
			for (String fieldName : attributes.keySet()) {
				FieldAccessor accessor = bean.getFieldAccessor(fieldName);
				append(json, accessor).append(COMMA);
            }
			json.deleteCharAt(json.length()-1);
		}
		json.append("}");
		return json.toString();
	}

	public static void main(String[] args) {
		StringBuilder json = new StringBuilder("{1111");
		System.out.println(json.deleteCharAt(json.length()-1));
		System.out.println(Collection.class.isAssignableFrom(ArrayList.class));
    }
	
	
}
