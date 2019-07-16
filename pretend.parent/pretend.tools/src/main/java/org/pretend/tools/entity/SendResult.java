package org.pretend.tools.entity;

import java.util.List;

public class SendResult<T> {

	private boolean successs;
	
	private String message;
	
	private List<T> fails;
	
	private List<T> sended;
	
	public boolean addFail(T t){
		if(null != t){
			return fails.add(t);
		}
		return false;
	}
	
	public boolean removeFail(T t){
		if(null != t){
			return fails.remove(t);
		}
		return false;
	}
	
	public boolean addSended(T t){
		if(null != t){
			return sended.add(t);
		}
		return false;
	}
	
	public boolean removeSended(T t){
		if(null != t){
			return sended.remove(t);
		}
		return false;
	}

	public boolean isSuccesss() {
		return successs;
	}

	public void setSuccesss(boolean successs) {
		this.successs = successs;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	
}
