package org.pretend.common.bean.abs;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.pretend.common.bean.api.AttributeAccessor;
import org.pretend.common.util.ObjectUtil;

public abstract class AbstarctAttribueAccessor implements AttributeAccessor {
	
	private Map<String,Object> attributes = new LinkedHashMap<String,Object>();

	public void setAttribute(String name, Object value) {
		ObjectUtil.notNull(name, "name must not be null!");
		attributes.put(name, value);
	}

	public Object getAttribute(String name) {
		ObjectUtil.notNull(name, "name must not be null!");
		return attributes.get(name);
	}

	public Object removeAttribute(String name) {
		ObjectUtil.notNull(name, "name must not be null!");
		return attributes.remove(name);
	}

	public boolean hasAttribute(String name) {
		ObjectUtil.notNull(name, "name must not be null!");
		return attributes.containsKey(name);
	}

	public String[] attributeNames() {
		
		return attributes.keySet().toArray(new String[attributes.keySet().size()]);
	}

	@Override
	public Map<String, Object> getAttributes() {
		
		return Collections.unmodifiableMap(attributes);
	}

	
	
}
