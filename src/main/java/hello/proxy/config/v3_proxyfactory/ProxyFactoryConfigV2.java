package hello.proxy.config.v3_proxyfactory;

import hello.advanced.trace.logtrace.LogTrace;
import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderReposiroryV1Impl;
import hello.proxy.app.v1.OrderReposirotyV1;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderReposiroryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfigV2 {

	@Bean
	public OrderReposiroryV2 orderReposirotyV2(LogTrace logTrace) {
		OrderReposiroryV2 orderReposirory = new OrderReposiroryV2();

		ProxyFactory factory = new ProxyFactory(orderReposirory);
		factory.addAdvisor(getAdvisor(logTrace));
		OrderReposiroryV2 proxy = (OrderReposiroryV2)factory.getProxy();
		log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderReposirory.getClass());
		return proxy;
	}

	@Bean
	public OrderServiceV2 orderServiceV2(LogTrace logTrace) {
		OrderServiceV2 orderService = new OrderServiceV2(orderReposirotyV2(logTrace));
		ProxyFactory factory = new ProxyFactory(orderService);
		OrderServiceV2 proxy = (OrderServiceV2)factory.getProxy();
		factory.addAdvisor(getAdvisor(logTrace));
		log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderService.getClass());
		return proxy;
	}

	@Bean
	public OrderControllerV2 orderControllerV2(LogTrace logTrace) {
		OrderControllerV2 orderController = new OrderControllerV2(orderServiceV2(logTrace));
		ProxyFactory factory = new ProxyFactory(orderController);
		OrderControllerV2 proxy = (OrderControllerV2)factory.getProxy();
		factory.addAdvisor(getAdvisor(logTrace));
		log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderController.getClass());
		return proxy;
	}
	private Advisor getAdvisor(LogTrace logTrace) {
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedNames("request*", "order*", "save*");

		LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);

		return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
	}
}
