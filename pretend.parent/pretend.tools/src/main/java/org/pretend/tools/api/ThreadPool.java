package org.pretend.tools.api;

import java.util.concurrent.Executor;

import org.pretend.tools.entity.Parameter;

public interface ThreadPool {

	Executor getExecutor(Parameter parameter);
}
