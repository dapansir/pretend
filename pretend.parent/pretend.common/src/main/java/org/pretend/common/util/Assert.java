package org.pretend.common.util;

public class Assert {
	
	private Assert(){
		
	}
	
	public static void notNull(Object o,String message){
		if(null == o){
			
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void hasText(String str,String message){
		if(null == str || str.trim().length() == 0){
			
			throw new IllegalArgumentException(message);
		}
	}

}
