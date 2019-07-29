package org.pretend.common.bean.abs;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.pretend.common.bean.FieldDefinition;
import org.pretend.common.bean.api.BeanMetadataInfo;
import org.pretend.common.bean.api.FieldAccessor;
import org.pretend.common.util.ClassHelper;
import org.pretend.common.util.ObjectUtil;

public abstract class AbstractBeanDefinition extends AbstarctAttribueAccessor implements BeanMetadataInfo,Serializable{

	private Object source;
	
	private Class<?> beanClass;
	
	private static final String INSTANCE_PROPERTIES = "self/praent_properties";
	
	private final Set<String> exclusiones = new HashSet<String>();
	
	
	public AbstractBeanDefinition() {
		super();
		initExculsiones();
	}
	
	private void initExculsiones(){
		exclusiones.add("serialVersionUID");
		exclusiones.add("PATTERN_NAME");
		exclusiones.add("PATTERN_MULTI_NAME");
		exclusiones.add("PATTERN_METHOD_NAME");
		exclusiones.add("PATTERN_PATH");
		exclusiones.add("PATTERN_NAME_HAS_SYMBOL");
		exclusiones.add("PATTERN_KEY");
		exclusiones.add("applicationContext");
		exclusiones.add("SPRING_CONTEXT");
		exclusiones.add("protocol");
		exclusiones.add("proxyFactory");
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
			setAllAttributes(getBeanClass(), ownProperties, true);
			setAttribute(INSTANCE_PROPERTIES, ownProperties);
		}
		return Collections.unmodifiableMap(ownProperties);
	}
	
	/**
	 * 获取clazz的所有属性值,包括除Object外所有的父类
	 * @param clazz
	 * @param ownProperties
	 * @param containsParent
	 */
	protected void setAllAttributes(Class<?>  clazz,Map<String, Object>  ownProperties,boolean containsParent) {
		ObjectUtil.notNull(ownProperties, "map cannot be null!");
		ownProperties.putAll(getOwnProperties(clazz));
		if(containsParent) {
			Class<?> parent = clazz.getSuperclass();
			if(!ClassHelper.javaSupperClass(parent)) {
				setAllAttributes(parent,ownProperties, containsParent);
			}
		}
	}
	
	/**
	 * 处理clazz直接声明的属性
	 * @param clazz
	 * @return
	 */
	private Map<String, Object> getOwnProperties(Class<?> clazz) {
		
		Map<String, Object> attributes = new HashMap<String, Object>();
		
		ObjectUtil.notNull(clazz, "clazz cannot be null!");
		
		Field[] declaredFields = clazz.getDeclaredFields();
		
		if (null != declaredFields && declaredFields.length > 0) {
			for (int i = 0; i < declaredFields.length; i++) {
				
				FieldDefinition fieldDefinition = new FieldDefinition(declaredFields[i], this.getSource());
				
				if(!needDeal(fieldDefinition.name())){
					continue;
				}
				
				dealField(fieldDefinition, attributes);
			}
		}
		
		return attributes;
	}
	
	/**
	 * 判断是否是需要处理的字段
	 * @param fieldName
	 * @return
	 */
	private boolean needDeal(String fieldName){
		
		boolean needDeal = true;
		if (exclusiones.contains(fieldName)) {
			needDeal = false;
		}
		return needDeal;
	}
	
	protected boolean resetExclusiones(Set<String> exclusiones){
		
		exclusiones.clear();
		return exclusiones.addAll(exclusiones);
	}
	
	protected boolean removeExclusion(String exclusion){
		
		return exclusiones.remove(exclusion);
	}
	
	protected boolean addExclusion(String exclusion){
		
		
		return exclusiones.add(exclusion);
	}
	
	/**
	 * 处理Field
	 * @param fieldDefinition
	 * @param attributes
	 */
	protected abstract void dealField(FieldAccessor fieldAccessor,Map<String, Object> attributes);
	
}
