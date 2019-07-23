package org.pretend.tools.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.pretend.common.Parameter;
import org.pretend.tools.api.ThreadPool;
import org.pretend.tools.constant.Constants;

public class LimitThreadPool implements ThreadPool {

	@Override
	public Executor getExecutor(Parameter parameter) {
		String threadName = parameter .get("threadName", Constants.DEFAULT_THREAD_NAME);
		int corePoolSize = parameter.get("corePoolSize",Constants.DEFAULT_CORE_THREADS);
		int maximumPoolSize = parameter.get("maximumPoolSize",Constants.DEFAULT_THREADS);
		int queueCapacity = parameter.get("queueCapacity", Constants.DEFAULT_QUEUES);
		return new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
				Long.MAX_VALUE, TimeUnit.MILLISECONDS,
				getQueue(queueCapacity), getThreadFactory(threadName),gethandler());
	}

	private BlockingQueue<Runnable> getQueue(int queueCapacity) {
		if (queueCapacity < 0) {
			return new LinkedBlockingQueue<Runnable>();
		}
		if (queueCapacity == 0) {
			return new SynchronousQueue<Runnable>();
		}
		return new LinkedBlockingQueue<Runnable>(queueCapacity);
	}

	private ThreadFactory getThreadFactory(String threadName) {

		return new NamedThreadFactory(threadName, true);
	}

	private RejectedExecutionHandler gethandler() {

		return new ThreadPoolExecutor.AbortPolicy();
	}
}
