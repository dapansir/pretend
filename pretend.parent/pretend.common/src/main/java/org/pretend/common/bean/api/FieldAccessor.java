package org.pretend.common.bean.api;

public interface FieldAccessor extends FieldDescriber {
	
	/**
	 * get the value of this field
	 * @return
	 */
	Object value();
	/**
	 * who declares
	 * @return
	 */
	Class<?> declareClass();
	/**
	 * name
	 * @return
	 */
	String name();
	/**
	 * FieldType
	 * @return
	 */
	Class<?> fieldType();

}
