package org.pretend.tools.api;

import java.util.concurrent.Executor;

import org.pretend.common.Parameter;

public interface ThreadPool {

	Executor getExecutor(Parameter parameter);
}
