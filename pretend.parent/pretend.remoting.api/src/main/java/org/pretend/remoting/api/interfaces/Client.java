package org.pretend.remoting.api.interfaces;

public interface Client extends Channel{
	
	void reconnect()throws RemotingException;
	
}
