package hello.proxy.config.v1_proxy;

import hello.advanced.trace.logtrace.LogTrace;
import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderReposiroryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

	@Bean
	public OrderControllerV2 orderControllerV2(LogTrace logTrace) {
		OrderControllerV2 controllerImpl = new OrderControllerV2(orderServiceV2(logTrace));
		return new OrderControllerConcreteProxy(controllerImpl, logTrace);
	}

	@Bean
	public OrderServiceV2 orderServiceV2(LogTrace logTrace) {
		OrderServiceV2 orderServiceV2 = new OrderServiceV2(orderReposiroryV2(logTrace));
		return new OrderServiceConcreteProxy(orderServiceV2, logTrace);
	}

	@Bean
	public OrderReposiroryV2 orderReposiroryV2(LogTrace logTrace) {
		OrderReposiroryV2 reposiroryImpl = new OrderReposiroryV2();
		return new OrderRepositoryConcreteProxy(reposiroryImpl, logTrace);
	}
}
