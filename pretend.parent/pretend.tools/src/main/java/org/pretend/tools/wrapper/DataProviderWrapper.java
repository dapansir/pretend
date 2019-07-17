package org.pretend.tools.wrapper;

import java.util.List;

import org.pretend.tools.api.DataProvider;

public class DataProviderWrapper<T> implements DataProvider<T>{
	
	private DataProvider<T> provider;
	
	public DataProviderWrapper(DataProvider<T> provider) {
		super();
		this.provider = provider;
	}

	public List<T> getData(){
		checkProvider();
		return provider.getData();
	}
	
	public List<T> getData(int beginIndex,int endIndex,String order){
		checkProvider();
		return provider.getData(beginIndex, endIndex, order);
	}
	
	private void checkProvider(){
		if(null == provider){
			throw new IllegalStateException("provider can not be null!");
		}
	}

	@Override
	public int[] generateData(List<T> data) {
		return provider.generateData(data);
	}

}
