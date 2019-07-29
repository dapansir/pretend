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
	

}
