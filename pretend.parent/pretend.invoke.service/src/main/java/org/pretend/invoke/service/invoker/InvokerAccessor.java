package org.pretend.invoke.service.invoker;

import java.util.List;

import com.alibaba.dubbo.rpc.Invocation;

public interface InvokerAccessor {
	
	List<Invocation> getInvokers();
	
}
