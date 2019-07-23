package org.pretend.tools.parse;

import java.lang.reflect.Method;

import org.pretend.common.Parameter;
import org.pretend.common.bean.BeanDefinition;
import org.pretend.common.util.ClassHelper;
import org.pretend.common.util.ObjectUtil;
import org.pretend.tools.constant.PretendTagName;
import org.pretend.tools.util.RegexpUtil;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public abstract class AbstractParser {
	
	private static final Parameter PARAMETER = new Parameter();
	
	static{
		PARAMETER.addParameter(PretendTagName.THREAD_POOL, PretendTagName.THREAD_POOL);
		PARAMETER.addParameter(PretendTagName.PROTOCOL, PretendTagName.PROTOCOL);
	}
	/**
	 * Turn the attribute of xml element to  java class property
	 * @param elementAttribute
	 * @return
	 */
	protected String getPropertyName(String elementAttribute){
		this.check(elementAttribute);
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
		this.check(elementAttribute);
		return this.split(elementAttribute);
	}
	
	/**
	 * RegexpUtil.split("-", elementAttribute)
	 * @param elementAttribute
	 * @return
	 */
	protected String[] split(String elementAttribute) {
		return RegexpUtil.split(getSplitRegexp(), elementAttribute);
	}
	
	
	
	protected void invoke(Method method,Class<?> parameterType,Object instance,Object value) throws Exception{
		
		if(parameterType.isPrimitive()){
			String stringValue = String.valueOf(value);
			//byte
			if(parameterType.equals(byte.class)){
				method.invoke(instance, Byte.parseByte(stringValue));
				return;
			}
			//short
			if(parameterType.equals(short.class)){
				method.invoke(instance, Short.parseShort(stringValue));
				return;
			}
			//char
			if(parameterType.equals(char.class)){
				method.invoke(instance, Character.valueOf(stringValue.charAt(0)));
				return;
			}
			//int
			if(parameterType.equals(int.class)){
				method.invoke(instance, Integer.parseInt(stringValue));
				return;
			}
			//float
			if(parameterType.equals(float.class)){
				method.invoke(instance, Float.parseFloat(stringValue));
				return;
			}
			//double
			if(parameterType.equals(double.class)){
				method.invoke(instance, Double.parseDouble(stringValue));
				return;
			}
			//long
			if(parameterType.equals(long.class)){
				method.invoke(instance, Long.parseLong(stringValue));
				return;
			}
			//boolean
			if(parameterType.equals(boolean.class)){
				method.invoke(instance, Boolean.parseBoolean(stringValue));
				return;
			}
		}else{
			method.invoke(instance,value);
		}

	}
	
	public BeanDefinition parse(Element element) {
		this.tagCheck(element.getLocalName());
		BeanDefinition bean = new BeanDefinition();
		NamedNodeMap attributes = element.getAttributes();
		//这几个可以不定义,但是为了可读性声明出来
		String attrName;
		String attrValue;
		String property;
		String setter;
		try {
			Object instance = this.getClazz().newInstance();
			for (int i = 0; i < attributes.getLength(); i++) {
				Node node = attributes.item(i);
				attrName = node.getNodeName();
				attrValue = node.getNodeValue();
				property = getPropertyName(attrName);
				setter = getSetterMethodName(attrName);
				try {
					if (ClassHelper.hasGetter(this.getClazz(), property) && ClassHelper.hasSetter(this.getClazz(), property)) {
						Class<?> parameterType = ClassHelper.getPropertyType( this.getClazz(), property);
						Method method = this.getClazz().getMethod(setter,new Class<?>[] { parameterType });
						this.invoke(method, parameterType, instance, attrValue);
						bean.setSource(instance);
						bean.setBeanClass(getClazz());
						bean.setAttribute(attrName, attrValue);
					}
				} catch (Exception e) {
					System.out.println("xml attribute["+node.getNodeName()+"]在["+this.getClazz().getCanonicalName()+"]中没有对应的property");
					//logger
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	protected void tagCheck(String tagName){
		if (!tagName.equals(PARAMETER.get(tagName))) {
			throw new IllegalArgumentException("The element is not a "+tagName+" element!");
		}
	}
	
	/**
	 * xml标签对应的类的class对象
	 * @return
	 */
	protected abstract Class<?> getClazz();
	
	/**
	 * 子类自定义
	 * @return
	 */
	protected abstract String getSplitRegexp();
	
}
