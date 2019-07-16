package org.pretend.tools.api;

import java.util.List;

import org.pretend.tools.entity.SendResult;

public interface DataSender<T> {

	public SendResult<T> send(List<T> data); 
}
