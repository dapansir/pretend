package org.pretend.remoting.api.interfaces;

import org.pretend.remoting.api.RemotingException;

public interface Client extends Channel{
	
	void reconnect()throws RemotingException;
	
}
