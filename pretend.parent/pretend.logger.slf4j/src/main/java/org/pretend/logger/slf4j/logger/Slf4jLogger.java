package org.pretend.logger.slf4j.logger;

import org.pretend.common.logger.Logger;

public class Slf4jLogger implements Logger {
	
	private org.slf4j.Logger  logger = null;
	
	public Slf4jLogger(){
		
	}
	
	public Slf4jLogger(org.slf4j.Logger  logger){
		this.logger = logger;
	}

	public void debug(String msg) {
		logger.debug(msg);
	}

	public void debug(String msg, Throwable e) {
		logger.debug(msg, e);
	}

	public void info(String msg) {
		logger.info(msg);
	}

	public void info(String msg, Throwable e) {
		logger.info(msg, e);
	}

	public void warn(String msg) {
		logger.warn(msg);
	}

	public void warn(String msg, Throwable e) {
		logger.warn(msg, e);
	}

	public void error(String msg) {
		logger.error(msg);
	}

	public void error(String msg, Throwable e) {
		logger.error(msg, e);
	}

	public void trace(String msg) {
		logger.trace(msg);
	}

	public void trace(String msg, Throwable e) {
		logger.trace(msg, e);
	}

}
