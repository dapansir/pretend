package org.pretend.web.controller.serialize;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.serializer.PropertyFilter;

public class SerializeFilter implements PropertyFilter {

private static final Set<String> EXCLUSIONES = new HashSet<String>();
	
	static {
		EXCLUSIONES.add("serialVersionUID");
		EXCLUSIONES.add("applicationContext");
		EXCLUSIONES.add("SPRING_CONTEXT");
		EXCLUSIONES.add("getDefaultPort");
		EXCLUSIONES.add("defaultPort");
		EXCLUSIONES.add("destroy");
	}
	@Override
	public boolean apply(Object object, String name, Object value) {
		if(null == value){
			return false;
		}
		return true;
	}

}
