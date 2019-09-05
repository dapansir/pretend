package org.pretend.common.bean.abs;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.pretend.common.bean.FieldDefinition;
import org.pretend.common.bean.api.BeanMetadataInfo;
import org.pretend.common.bean.api.FieldAccessor;
import org.pretend.common.util.ClassHelper;
import org.pretend.common.util.ObjectUtil;

public abstract class AbstractBeanDefinition extends AbstarctAttribueAccessor implements BeanMetadataInfo,Serializable{

	private Object source;
	
	private Class<?> beanClass;
	
	private final Set<String> exclusiones = new HashSet<String>();
	
	private Map<String,FieldAccessor> accessores = new ConcurrentHashMap<String, FieldAccessor>();
	
	private boolean attributeSetted = false;
	
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
	

	@Override
	public Map<String, Object> getAttributes() {
		if(!attributeSetted){
			setAllAttributes(getBeanClass(),true);
		}
		return super.getAttributes();
	}
	
	/**
	 * 获取clazz的所有属性值,包括除Object外所有的父类
	 * @param clazz
	 * @param ownProperties
	 * @param containsParent
	 */
	protected void setAllAttributes(Class<?>  clazz,boolean containsParent) {
		setOwnProperties(clazz);
		if(containsParent) {
			Class<?> parent = clazz.getSuperclass();
			if(!ClassHelper.javaSupperClass(parent)) {
				setAllAttributes(parent,containsParent);
			}
		}
		attributeSetted = true;
	}
	
	/**
	 * 处理clazz直接声明的属性
	 * @param clazz
	 * @return
	 */
	private void setOwnProperties(Class<?> clazz) {
		
		ObjectUtil.notNull(clazz, "clazz cannot be null!");
		
		Field[] declaredFields = clazz.getDeclaredFields();
		
		if (null != declaredFields && declaredFields.length > 0) {
			
			for (int i = 0; i < declaredFields.length; i++) {
				
				FieldDefinition field = new FieldDefinition(declaredFields[i], this.getSource());
				
				if(!needDeal(field.name())){
					continue;
				}
				
				accessores.put(field.name(), field);
				
				setAttribute(field.name(), field.value());
			}
		}
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
	
	public FieldAccessor getFieldAccessor(String fieldName){
		
		return accessores.get(fieldName);
	}
	
}
