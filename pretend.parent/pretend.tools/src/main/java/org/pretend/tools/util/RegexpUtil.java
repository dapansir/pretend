package org.pretend.tools.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pretend.tools.constant.RegexpStr;

public class RegexpUtil {
	
	private RegexpUtil() {
		
	}
	
	private static final Pattern DATE_PATTERNER = Pattern.compile(RegexpStr.DATE_REG);
	
	private static final Pattern SPACE_PATTERNER = Pattern.compile(RegexpStr.SPACE_REG);
	
	private static final Pattern LETTER_PATTERNER = Pattern.compile(RegexpStr.LETTER_REG);
	
	private static final Pattern NUMBER_LETTER_PATTERNER = Pattern.compile(RegexpStr.NUM_AND_LETTER_REG);
	
	private static final Pattern MAIL_PATTERNER = Pattern.compile(RegexpStr.MAIL_REG);
	
	private static final Pattern MOBILE_PHONE_PATTERNER = Pattern.compile(RegexpStr.MOBILE_PHONE_REG);
	
	private static final Pattern NUMBER_PATTERNER = Pattern.compile(RegexpStr.NUM_REG);
	
	public static boolean regDate(String input){
		Matcher matcher = DATE_PATTERNER.matcher(input);
		return matcher.matches();
	}
	
	public static boolean regSpace(String input){
		Matcher matcher = SPACE_PATTERNER.matcher(input);
		return matcher.matches();
	}
	
	public static boolean regSpace(String input,boolean trim){
		if(trim){
			input = trim(input);
		}
		Matcher matcher = SPACE_PATTERNER.matcher(input);
		return matcher.matches();
	}
	
	public static boolean regNumber(String input){
		Matcher matcher = NUMBER_PATTERNER.matcher(input);
		return matcher.matches();
	}
	
	public static boolean regLetter(String input){
		Matcher matcher = LETTER_PATTERNER.matcher(input);
		return matcher.matches();
	}
	
	public static boolean regNumLetter(String input){
		Matcher matcher = NUMBER_LETTER_PATTERNER.matcher(input);
		return matcher.matches();
	}
	
	public static boolean regMobliePhone(String input){
		Matcher matcher = MOBILE_PHONE_PATTERNER.matcher(input);
		return matcher.matches();
	}
	
	public static boolean regMail(String mail){
		Matcher matcher = MAIL_PATTERNER.matcher(mail);
		return  matcher.matches();
	}
	
	public static boolean isNumber(String input,boolean allowSpace) throws IllegalAccessException{
		if(null == input || "".equals(input)){
			return false;
		}
		if(allowSpace){
			input = input.trim();
		}
		Matcher matcher  = SPACE_PATTERNER.matcher(input);
		if(matcher.matches()){
			throw new IllegalAccessException("传入的值["+input+"]中间含有空格");
		}
		matcher = NUMBER_PATTERNER.matcher(input);
		
		return matcher.matches();
	}
	
	private static final String trim(String input){
		String str = input;
		if(null == str || "".equals(str)){
			return str;
		}
		return str.trim();
	}
	
	public static String[] split(String regexp,String input){
		Pattern split = Pattern.compile(regexp);
		return split.split(input);
	}
	

}
