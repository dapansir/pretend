package org.pretend.tools.api;

import java.util.List;

public interface DataProvider<T> {
	
	List<T> getData();
	
	List<T> getData(int beginIndex,int endIndex,String order);
	
	int[] generateData(List<T> data);

}
