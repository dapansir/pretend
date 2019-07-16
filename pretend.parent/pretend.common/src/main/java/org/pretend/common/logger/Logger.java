package org.pretend.common.logger;

public interface Logger {

	void debug(String msg);
	
	void debug(String msg,Throwable e);
	
	void info(String msg);
	
	void info(String msg,Throwable e);
	
	void warn(String msg);
	
	void warn(String msg,Throwable e);
	
	void error(String msg);
	
	void error(String msg,Throwable e);
	
	void trace(String msg);
	
	void trace(String msg,Throwable e);
}
