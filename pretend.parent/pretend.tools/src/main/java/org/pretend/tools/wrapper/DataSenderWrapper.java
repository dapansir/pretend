package org.pretend.tools.wrapper;

import java.util.List;

import org.pretend.tools.api.DataSender;
import org.pretend.tools.entity.SendResult;

public class DataSenderWrapper<T> implements DataSender<T> {

	private DataSender<T> sender;
	
	public DataSenderWrapper(DataSender<T> sender) {
		super();
		this.sender = sender;
	}

	@Override
	public SendResult<T> send(List<T> data) {
		checkSender();
		return sender.send(data);
	}
	
	private void checkSender(){
		if(null == sender){
			throw new IllegalStateException("provider can not be null!");
		}
	}

}
