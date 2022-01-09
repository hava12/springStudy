package hello.proxy.config.v1_proxy;

import hello.advanced.trace.logtrace.LogTrace;
import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderReposiroryV1Impl;
import hello.proxy.app.v1.OrderReposirotyV1;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

	@Bean
	public OrderControllerV1 orderController(LogTrace logTrace) {
		OrderControllerV1Impl controllerImpl = new OrderControllerV1Impl(orderService(logTrace));
		return new OrderControllerInterfaceProxy(controllerImpl, logTrace);
	}

	@Bean
	public OrderServiceV1 orderService(LogTrace logTrace) {
		OrderServiceV1Impl orderServiceV1Impl = new OrderServiceV1Impl(orderReposiroty(logTrace));
		return new OrderServiceInterfaceProxy(orderServiceV1Impl, logTrace);
	}

	@Bean
	public OrderReposirotyV1 orderReposiroty(LogTrace logTrace) {
		OrderReposiroryV1Impl orderReposiroryV1Impl = new OrderReposiroryV1Impl();
		return new OrderRepositoryInterfaceProxy(orderReposiroryV1Impl, logTrace);
	}
}
