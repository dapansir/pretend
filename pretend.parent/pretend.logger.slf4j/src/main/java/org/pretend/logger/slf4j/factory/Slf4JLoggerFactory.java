package org.pretend.logger.slf4j.factory;

import org.pretend.common.annotation.Activity;
import org.pretend.common.logger.Logger;
import org.pretend.common.logger.LoggerFactory;
import org.pretend.logger.slf4j.logger.Slf4jLogger;

@Activity
public class Slf4JLoggerFactory implements LoggerFactory {
	
//	private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Slf4JLoggerFactory.class);

	public Logger getLogger(Class<?> clazz) {
		return new Slf4jLogger(org.slf4j.LoggerFactory.getLogger(clazz));
	}

	public Logger getLogger() {
		throw new IllegalStateException("使用slf4j日志,必须传入获取logger的参数!");
	}

	public Logger getLogger(String name) {
		return new Slf4jLogger(org.slf4j.LoggerFactory.getLogger(name));
	}

}
