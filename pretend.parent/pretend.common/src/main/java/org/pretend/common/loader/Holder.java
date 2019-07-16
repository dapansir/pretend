package org.pretend.common.loader;

public class Holder<T> {

	private volatile T instance ;

	public T getInstance() {
		return instance;
	}

	public void setInstance(T instance) {
		this.instance = instance;
	} 
	
	
	
}
