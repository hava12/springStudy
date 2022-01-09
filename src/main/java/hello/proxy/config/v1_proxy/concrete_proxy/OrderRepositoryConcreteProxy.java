package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.proxy.app.v2.OrderReposiroryV2;

public class OrderRepositoryConcreteProxy extends OrderReposiroryV2 {
	private final OrderReposiroryV2 target;
	private final LogTrace logTrace;

	public OrderRepositoryConcreteProxy(OrderReposiroryV2 target, LogTrace logTrace) {
		this.target = target;
		this.logTrace = logTrace;
	}

	@Override
	public String save(String itemId) {
		TraceStatus status = null;
		try {
			status = logTrace.begin("OrderRespository.request()");
			target.save(itemId);
			logTrace.end(status);
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
		return null;
	}
}
