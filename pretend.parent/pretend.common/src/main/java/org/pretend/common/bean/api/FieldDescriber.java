package org.pretend.common.bean.api;

public interface FieldDescriber extends BeanMetadataInfo {
	/**
	 * static
	 * @return
	 */
	boolean isStatic();
	/**
	 * 
	 * @return
	 */
	String deClaredBy();
	/**
	 * can assess
	 * @return
	 */
	boolean accessiable();
	/**
	 * public
	 * @return
	 */
	boolean isPublic();
	/**
	 * protected
	 * @return
	 */
	boolean isProtected();
	/**
	 * private
	 * @return
	 */
	boolean isPrivate();
	/**
	 * final
	 * @return
	 */
	boolean isFinal();
	/**
	 * String 类型
	 * @return
	 */
	boolean isString();
	/**
	 * 数字类型
	 * @return
	 */
	boolean isNumber();
	/**
	 * 空值
	 * @return
	 */
	boolean isValueNull();
	/**
	 * 布尔类型
	 * @return
	 */
	boolean isBoolean();

}
