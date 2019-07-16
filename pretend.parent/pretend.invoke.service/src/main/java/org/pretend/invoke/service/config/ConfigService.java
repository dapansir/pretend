package org.pretend.invoke.service.config;


public interface ConfigService {

	<T> T getConfig(Class<T> clazz);
}
