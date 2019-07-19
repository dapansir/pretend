package org.pretend.server.api;

import java.sql.Timestamp;
import java.util.List;

public interface ParseResult {

	boolean success();
	
	String errorMsg();
	
	Timestamp dealTime();
	
	List<Object> getParseData();
	
}
