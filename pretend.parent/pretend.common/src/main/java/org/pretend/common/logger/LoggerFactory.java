package org.pretend.common.logger;

public interface LoggerFactory {
	/**
	 * 
	 * @return
	 */
	Logger getLogger();
	/**
	 * 
	 * @param name
	 * @return
	 */
	Logger getLogger(String name);
	/**
	 * 
	 * @param clazz
	 * @return
	 */
	Logger getLogger(Class<?> clazz);
	
	
}
