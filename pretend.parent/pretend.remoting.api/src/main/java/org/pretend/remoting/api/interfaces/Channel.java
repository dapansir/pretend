package org.pretend.remoting.api.interfaces;

import java.net.InetSocketAddress;

public interface Channel extends Endpoint{

	 /**
     * 获取远程服务器的地址
     *
     * @return remote address.
     */
    InetSocketAddress getRemoteAddress();

    /**
     * is connected.
     *
     * @return connected
     */
    boolean isConnected();

    /**
     * has attribute.
     *
     * @param key key.
     * @return has or has not.
     */
    boolean hasAttribute(String key);

    /**
     * get attribute.
     *
     * @param key key.
     * @return value.
     */
    Object getAttribute(String key);

    /**
     * set attribute.
     *
     * @param key   key.
     * @param value value.
     */
    void setAttribute(String key, Object value);

    /**
     * remove attribute.
     *
     * @param key key.
     */
    Object removeAttribute(String key);
    
	/**
	 * 
	 * @return
	 */
	String getChannelName();
   
	
}
