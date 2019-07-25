package org.pretend.remoting.api.monitor;

public interface ConfigService<T> {
	
	/**
	 * 获取客户端/客户端的配置
	 * @param clazz
	 * @return
	 */
	T getConfig(Class<T> clazz);
	
}
