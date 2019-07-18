package org.pretend.tools.config;

import java.security.Timestamp;

public class ProtocolConfig extends AbstractConfig{
	
	private Timestamp timestamp;

	private String addrress;
	
	private long timeout;
	
	private long heartbeat;
	
	private String handler;
	
	private String url;
	
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAddrress() {
		return addrress;
	}

	public void setAddrress(String addrress) {
		this.addrress = addrress;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public long getHeartbeat() {
		return heartbeat;
	}

	public void setHeartbeat(long heartbeat) {
		this.heartbeat = heartbeat;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
