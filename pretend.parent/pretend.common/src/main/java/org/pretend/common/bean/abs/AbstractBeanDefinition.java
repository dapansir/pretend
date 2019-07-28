package org.pretend.common.bean.abs;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.pretend.common.bean.api.BeanMetadataInfo;
import org.pretend.common.util.ClassHelper;
import org.pretend.common.util.ObjectUtil;

public abstract class AbstractBeanDefinition extends AbstarctAttribueAccessor implements BeanMetadataInfo,Serializable{

	private Object source;
	
	private Class<?> beanClass;
	
	private static final String INSTANCE_PROPERTIES = "self/praent_properties";
	
	private static final Set<String> EXCLUSIONES = new HashSet<String>();
	
	static {
		EXCLUSIONES.add("serialVersionUID");
		EXCLUSIONES.add("PATTERN_NAME");
		EXCLUSIONES.add("PATTERN_MULTI_NAME");
		EXCLUSIONES.add("PATTERN_METHOD_NAME");
		EXCLUSIONES.add("PATTERN_PATH");
		EXCLUSIONES.add("PATTERN_NAME_HAS_SYMBOL");
		EXCLUSIONES.add("PATTERN_KEY");
		EXCLUSIONES.add("applicationContext");
		EXCLUSIONES.add("SPRING_CONTEXT");
		EXCLUSIONES.add("protocol");
		EXCLUSIONES.add("proxyFactory");
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Object getSource() {
		return source;
	}
	
	public void setSource(Object source){
		this.source = source;
	}

	public Class<?> getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}
	
	public String getBeanClassName(){
		return this.beanClass.getName();
	}

	@Override
	public String toString() {
		return "AbstractBeanDefinition [source=" + source + ", beanClass="
				+ beanClass + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getAttributes() {
		Map<String, Object>  ownProperties = (Map<String, Object>) getAttribute(INSTANCE_PROPERTIES);
		if(ownProperties == null) {
			ownProperties = new HashMap<String, Object>();
			setOwnAttributes(getBeanClass(), ownProperties, true);
			setAttribute(INSTANCE_PROPERTIES, ownProperties);
		}
		return Collections.unmodifiableMap(ownProperties);
	}
	
	protected void setOwnAttributes(Class<?>  clazz,Map<String, Object>  ownProperties,boolean containsParent) {
		ObjectUtil.notNull(ownProperties, "map cannot be null!");
		ownProperties.putAll(getOwnProperties(clazz));
		if(containsParent) {
			Class<?> parent = clazz.getSuperclass();
			if(!ClassHelper.javaSupperClass(parent)) {
				setOwnAttributes(parent,ownProperties, containsParent);
			}
		}
	}
	
	private  Map<String, Object> getOwnProperties(Class<?> clazz){
		Map<String, Object> attributes = new HashMap<String, Object>();
		ObjectUtil.notNull(clazz, "clazz cannot be null!");
		Field[] declaredFields = clazz.getDeclaredFields();
		if(null != declaredFields && declaredFields.length >0) {
			for (int i = 0; i < declaredFields.length; i++) {
				Field field = declaredFields[i];
				String fieldName = field.getName();
				if(EXCLUSIONES.contains(fieldName)) {
					continue;
				}
				if(Modifier.isStatic(field.getModifiers())) {
					
					try {
						boolean accessiable = field.isAccessible();
						if (!accessiable) {
							field.setAccessible(true);
						}
						attributes.put(fieldName, field.get(this.getSource()));
						field.setAccessible(accessiable);
						continue;
					} catch (Exception e) {
						e.printStackTrace();
					}
					 
//					continue;
				}
				Method method = ClassHelper.getGetterMethod(clazz,fieldName);
				/*
				 * if("ref".equals(fieldName)) { try { Type superclass = clazz.getg
				 * ParameterizedType pt = (ParameterizedType) clazz.getGenericSuperclass();
				 * Class<?> type = (Class<?>) pt.getActualTypeArguments()[0]; method =
				 * clazz.getMethod("getServiceClass",type); Class<?> ref = (Class<?>)
				 * method.invoke(this.getSource()); attributes.put(fieldName, ref.getName());
				 * continue; } catch (Exception e) { e.printStackTrace(); } }
				 */
				if(null != method) {
					try {
						attributes.put(fieldName, method.invoke(this.getSource()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return attributes;
	}
	
}
