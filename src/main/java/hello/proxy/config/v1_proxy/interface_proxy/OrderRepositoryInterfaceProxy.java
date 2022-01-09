package hello.proxy.config.v1_proxy.interface_proxy;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.proxy.app.v1.OrderReposirotyV1;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderReposirotyV1 {

	private final OrderReposirotyV1 target;
	private final LogTrace logTrace;

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
