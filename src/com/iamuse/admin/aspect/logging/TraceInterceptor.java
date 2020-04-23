package com.iamuse.admin.aspect.logging;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;

public class TraceInterceptor extends CustomizableTraceInterceptor {


	private static final long serialVersionUID = 1L;


	@Override
	protected void writeToLog(Log logger, String message, Throwable ex) {
		if (ex != null) {
			logger.error(message);
		} else {
			logger.info(message);
		}
	}
    

	@Override
	protected boolean isInterceptorEnabled(MethodInvocation invocation, Log logger) {
		return true;
	}
}