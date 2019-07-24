package org.pretend.remoting.api.abs;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.pretend.common.Parameter;
import org.pretend.remoting.api.interfaces.Channel;
import org.pretend.remoting.api.interfaces.ChannelHandler;

public abstract class AbstractChannel extends AbstractPeer implements Channel {

	
	public AbstractChannel(Parameter parameter, ChannelHandler handler) {
		super(parameter, handler);
	}

	protected static final String CHANNEL_NAME_STR = "channel_name";
	
	private Map<String,Object> attrributes = new ConcurrentHashMap<String,Object>();
	
	protected void checkNull(String str){
		if(null == str || "".equals(str.trim())){
			throw new IllegalArgumentException("Argument cannot be null!");
		}
	}

	@Override
	public String getChannelName() {
		
		return String.valueOf(attrributes.get(CHANNEL_NAME_STR));
	}
	
	@Override
	public boolean hasAttribute(String key) {
		checkNull(key);
		return attrributes.containsKey(key);
	}

	@Override
	public Object getAttribute(String key) {
		checkNull(key);
		if(null == key){
			throw new IllegalArgumentException("Argument cannot be null!");
		}
		return attrributes.get(key);
	}

	@Override
	public void setAttribute(String key, Object value) {
		checkNull(key);
		if(null == value){
			attrributes.remove(key);
		}else{
			attrributes.put(key, value);
		}

	}

	@Override
	public Object removeAttribute(String key) {
		checkNull(key);
		return attrributes.remove(key);
	}
	
	protected abstract void setChannelName(String channelName);
	
}
