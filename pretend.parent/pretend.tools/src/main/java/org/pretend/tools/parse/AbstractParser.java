package org.pretend.tools.parse;

import org.pretend.common.util.ClassHelper;
import org.pretend.common.util.ObjectUtil;
import org.pretend.tools.util.RegexpUtil;
import org.w3c.dom.Element;

public abstract class AbstractParser {
	
	/**
	 * Turn the attribute of xml element to  java class property
	 * @param elementAttribute
	 * @return
	 */
	protected String getPropertyName(String elementAttribute){
		check(elementAttribute);
		String[] str = getSplit(elementAttribute);
		StringBuilder property = new StringBuilder();
		if(str.length == 1) {
			return str[0];
		}
		property.append(str[0]);
		for (int i = 1; i < str.length; i++) {
			property.append(ClassHelper.toMethodsuffix(str[i]));
		}
		return property.toString();
	}
	
	/**
	 * Turn to a method name ,such as "setPersonName"
	 * @param prefix like get/set
	 * @param elementAttribute like person_name 
	 * @return
	 */
	protected String getMethodName(String prefix,String elementAttribute) {
		StringBuilder sb = new StringBuilder();
		String property = getPropertyName(elementAttribute);
		if(property.length()>1) {
			property = ClassHelper.toMethodsuffix(property);
		}
		sb.append(prefix).append(getPropertyName(property));
		return sb.toString();
	}
	
	/**
	 * get the setter method name
	 * @param elementAttribute
	 * @return
	 */
	protected String getSetterMethodName(String elementAttribute) {
		return getMethodName("set", elementAttribute);
	}
	/**
	 * get the getter method name
	 * @param elementAttribute
	 * @return
	 */
	protected String getGetterMethodName(String elementAttribute) {
		return getMethodName("get", elementAttribute);
	}

	/**
	 * null check ,cannot contains space!throw a IllegalArgumentException otherwise!
	 * @param toCheck
	 */
	protected void check(Object toCheck) {
		ObjectUtil.notNull(toCheck, "must not be null!");
		if(toCheck instanceof String) {
			if (RegexpUtil.regSpace(String.valueOf(toCheck))) {
				throw new IllegalArgumentException("must not be null!");
			}
		}
	}
	
	protected String[] getSplit(String elementAttribute) {
		check(elementAttribute);
		return split(elementAttribute);
	}
	
	/**
	 * RegexpUtil.split("-", elementAttribute)
	 * @param elementAttribute
	 * @return
	 */
	protected String[] split(String elementAttribute) {
		return RegexpUtil.split(getSplitRegexp(), elementAttribute);
	}
	
	/**
	 * 子类自定义
	 * @return
	 */
	protected abstract String getSplitRegexp();
	
	public void printElement(Element element) {
		System.out.println(element.getPrefix());
		System.out.println(element.getTagName());
		System.out.println(element.getLocalName());
	}
	
}
