package org.pretend.tools.runner;

import java.util.List;

import org.pretend.common.loader.ExtensionLoader;
import org.pretend.tools.api.DataProvider;
import org.pretend.tools.api.DataSender;
import org.pretend.tools.api.ResultHandler;
import org.pretend.tools.entity.SendResult;

public class SenderRunner<T> implements Runnable {
	
	@SuppressWarnings("unchecked")
	private DataSender<T> sender = ExtensionLoader.getExtensionLoader(DataSender.class).getActiveExtension();
	
	@SuppressWarnings("unchecked")
	private DataProvider<T> provider = ExtensionLoader.getExtensionLoader(DataProvider.class).getActiveExtension();
	
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
