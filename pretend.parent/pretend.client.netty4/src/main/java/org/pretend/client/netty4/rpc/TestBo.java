package org.pretend.client.netty4.rpc;

import java.io.Serializable;

public class TestBo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8927211958690103126L;

	private String name;
	
	private String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	
}
