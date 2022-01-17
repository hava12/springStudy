package hello.proxy.config.v3_proxyfactory.advice;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class LogTraceAdvice implements MethodInterceptor {

	public LogTraceAdvice(LogTrace logTrace) {
		this.logTrace = logTrace;
	}

	private final LogTrace logTrace;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		TraceStatus status = null;
		try {
			String message = invocation.getMethod().getDeclaringClass().getSimpleName() + "." + invocation.getMethod().getName() + "()";
			status = logTrace.begin(message);

			//로직 호출
			Object result = invocation.proceed();
			logTrace.end(status);

			return result;
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
}
