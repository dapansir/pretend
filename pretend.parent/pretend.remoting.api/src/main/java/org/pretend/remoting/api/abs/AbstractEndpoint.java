package org.pretend.remoting.api.abs;

import org.pretend.common.Parameter;
import org.pretend.remoting.api.interfaces.ChannelHandler;
import org.pretend.remoting.api.interfaces.Endpoint;
import org.pretend.remoting.api.interfaces.Resetable;

public abstract class AbstractEndpoint extends AbstractPeer implements Endpoint,Resetable{

	public AbstractEndpoint(Parameter parameter, ChannelHandler handler) {
		super(parameter, handler);
	}

	@Override
	public void reset(Parameter parameter) {
		if (isClosed()) {
            throw new IllegalStateException("Failed to reset parameters "
                    + parameter + ", cause: Channel closed. channel: " + getLocalAddress());
        }
	}
	
}
