package org.pretend.common.util;

public class ObjectUtil {
	
	private ObjectUtil(){
		
	}
	
	public static void notNull(Object value,String message){
		if(null == value){
			throw new IllegalArgumentException(message);
		}
		if(message instanceof String){
			String str = (String)value;
			if("".equals(str.trim())){
				throw new IllegalArgumentException(message);
			}
		}
	}
	
}
