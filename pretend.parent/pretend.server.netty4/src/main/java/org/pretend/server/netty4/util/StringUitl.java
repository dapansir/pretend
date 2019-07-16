package org.pretend.server.netty4.util;

public class StringUitl {

	
	private StringUitl(){
		
	}
	
	public static boolean isEmpty(String str,boolean allowBlank){
		if(allowBlank){
			return null == str;
		}else{
			return null == str || "".equals(str.trim());
		}
	}
	
	public static boolean isEmpty(String str){
		return isEmpty(str,false);
	}
}
