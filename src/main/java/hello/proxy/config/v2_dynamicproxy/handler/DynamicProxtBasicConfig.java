package hello.proxy.config.v2_dynamicproxy.handler;

import hello.advanced.trace.logtrace.LogTrace;
import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderReposiroryV1Impl;
import hello.proxy.app.v1.OrderReposirotyV1;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import java.lang.reflect.Proxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamicProxtBasicConfig {

	@Bean
	public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
		OrderControllerV1 orderControllerV1 = new OrderControllerV1Impl(orderServiceV1(logTrace));
		OrderControllerV1 proxy =
			(OrderControllerV1)Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(), new Class[]{OrderControllerV1.class}, new LogTraceBasicHandler(orderControllerV1, logTrace));
		return proxy;
	}

	@Bean
	public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
		OrderServiceV1 orderService = new OrderServiceV1Impl(orderReposirotyV1(logTrace));
		OrderServiceV1 proxy = (OrderServiceV1)Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(), new Class[] {OrderServiceV1.class},
			new LogTraceBasicHandler(orderService, logTrace));
		return proxy;
	}

	@Bean
	public OrderReposirotyV1 orderReposirotyV1(LogTrace logTrace) {
		OrderReposirotyV1 orderRepository = new OrderReposiroryV1Impl();

		OrderReposirotyV1 proxy =
			(OrderReposirotyV1)Proxy.newProxyInstance(OrderReposirotyV1.class.getClassLoader(), new Class[] {OrderReposirotyV1.class},
				new LogTraceBasicHandler(orderRepository, logTrace));
		return proxy;
	}
}
