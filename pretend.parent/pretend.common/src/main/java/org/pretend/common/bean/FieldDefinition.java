package org.pretend.common.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.pretend.common.bean.api.FieldAccessor;
import org.pretend.common.util.ObjectUtil;

public class FieldDefinition implements FieldAccessor {
	
	private Field field;
	
	private int modifiers = -1;
	
	private Object instance;

	public FieldDefinition(Field field,Object instance) {
		super();
		ObjectUtil.notNull(field, "field must not be null!");
		this.field = field;
		this.instance = instance;
		this.modifiers = field.getModifiers();
	}

	@Override
	public boolean isStatic() {
		
		return Modifier.isStatic(modifiers);
	}

	@Override
	public String deClaredBy() {
		
		return field.getDeclaringClass().getName();
	}

	@Override
	public boolean accessiable() {
		
		return field.isAccessible();
	}

	@Override
	public boolean isPublic() {
		
		return Modifier.isPublic(modifiers);
	}

	@Override
	public boolean isProtected() {
		
		return Modifier.isProtected(modifiers);
	}

	@Override
	public boolean isPrivate() {
		
		return Modifier.isPrivate(modifiers);
	}

	@Override
	public boolean isFinal() {
		
		return Modifier.isFinal(modifiers);
	}

	@Override
	public Object getSource() {
		
		return this.field;
	}

	@Override
	public Object value() {
		Object value = null;
		synchronized (this) {
			boolean accessible = field.isAccessible();
			if(!accessible){
				field.setAccessible(true);
			}
			//只有static的属性,才可以传null值
			if(null == instance && !isStatic()){
				return value;
			}
			try {
				value = field.get(instance);
			} catch (Exception e) {
				//nothing to do
			}
			field.setAccessible(accessible);
		}
		return value;
	}

	@Override
	public Class<?> declareClass() {
		
		return this.field.getDeclaringClass();
	}

	@Override
	public String name() {
		
		return this.field.getName();
	}

	@Override
    public Class<?> fieldType() {
		
	    return this.field.getType();
    }

}
