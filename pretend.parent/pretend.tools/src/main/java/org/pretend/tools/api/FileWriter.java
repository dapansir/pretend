package org.pretend.tools.api;

/**
 * 
 * 文件操作接口
 */
public interface FileWriter {

	void writeString(String content);
	
	void writeObject(Object content);
	
	long mark();
	
	void writeFromMark();
	
}
