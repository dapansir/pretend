package org.pretend.tools.parse.element;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.pretend.common.bean.BeanDefinition;
import org.pretend.common.util.ClassHelper;
import org.pretend.tools.api.Parser;
import org.pretend.tools.config.ThreadPoolConfig;
import org.pretend.tools.constant.PretendTagName;
import org.pretend.tools.parse.AbstractParser;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class ThreadPoolParser extends AbstractParser implements Parser {

	private Class<?> clazz;
	
	private final String regexpStr = "_";
	
	public ThreadPoolParser(Class<?> clazz) {
		super();
		this.clazz = clazz;
	}

	@Override
	public BeanDefinition parse(Element element) {
		if(!PretendTagName.THREAD_POOL.equals(element.getLocalName())){
			throw new IllegalArgumentException("The element is not a threadpool element!");
		}
		BeanDefinition bean = new BeanDefinition();
		NamedNodeMap attributes = element.getAttributes();
		String attrName = null;
		String attrValue = null;
		for (int i = 0; i < attributes.getLength(); i++) {
			Node node = attributes.item(i);
			attrName = node.getNodeName();
			attrValue = node.getNodeValue();
			String property = getPropertyName(attrName);
			String setter = getSetterMethodName(attrName);
			if(ClassHelper.hasGetter(clazz, property) && ClassHelper.hasSetter(clazz, property)) {
				Class<?> parameterType = ClassHelper.getPropertyType(clazz, property);
				try {
					Method method = clazz.getMethod(setter, new Class<?>[] {parameterType});
					ThreadPoolConfig config = (ThreadPoolConfig) clazz.newInstance();
					method.invoke(config, attrValue);
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				}
			}
		}
		return bean;
	}

	@Override
	protected String getSplitRegexp() {
		return regexpStr;
	}

}
