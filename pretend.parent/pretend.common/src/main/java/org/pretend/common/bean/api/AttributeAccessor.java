package org.pretend.common.bean.api;

public interface AttributeAccessor {
	
	/**
	 * 保存属性值
	 * @param name
	 * @param value
	 */
	void setAttribute(String name, Object value);
	/**
	 * 获取属性值
	 * @param name
	 * @return
	 */
	Object getAttribute(String name);
	/**
	 * 删除属性
	 * @param name
	 * @return
	 */
	Object removeAttribute(String name);
	/**
	 * 查看是否包含该属性
	 * @param name
	 * @return
	 */
	boolean hasAttribute(String name);
	/**
	 * 所有属性名
	 * @return
	 */
	String[] attributeNames();
}
