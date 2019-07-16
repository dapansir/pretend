package org.pretend.tools.runner;

import java.util.List;

import org.pretend.common.loader.ExtensionLoader;
import org.pretend.tools.api.ResultHandler;
import org.pretend.tools.entity.SendResult;
import org.pretend.tools.wrapper.DataProviderWrapper;
import org.pretend.tools.wrapper.DataSenderWrapper;

public class SenderRunner<T> implements Runnable {
	
	private DataSenderWrapper<T> sender = new DataSenderWrapper<T>(); 
	
	private DataProviderWrapper<T> provider = new DataProviderWrapper<T>();
	
	private ResultHandler exceptionHandler = ExtensionLoader.getExtensionLoader(ResultHandler.class).getExtension("exception");
	
	private ResultHandler suceessHandler = ExtensionLoader.getExtensionLoader(ResultHandler.class).getExtension("success"); 
	
	private int start;
	
	private int end;
	
	private String order;
	

	public SenderRunner(int start, int end, String order) {
		super();
		this.start = start;
		this.end = end;
		this.order = order;
	}




	@Override
	public void run() {
		SendResult<T> result = new SendResult<T>();
		try {
			List<T> data = provider.getData(start, end, order);
			result = sender.send(data);
			suceessHandler.handler(result);
		} catch (Exception e) {
			exceptionHandler.handler(e,result);
		}
	}
	
	

}
