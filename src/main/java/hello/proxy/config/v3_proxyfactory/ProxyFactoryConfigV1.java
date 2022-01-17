package hello.proxy.config.v3_proxyfactory;

import hello.advanced.trace.logtrace.LogTrace;
import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderReposiroryV1Impl;
import hello.proxy.app.v1.OrderReposirotyV1;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
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
public class ProxyFactoryConfigV1 {

	@Bean
	public OrderReposirotyV1 orderReposirotyV1(LogTrace logTrace) {
		OrderReposiroryV1Impl orderReposirory = new OrderReposiroryV1Impl();

		ProxyFactory factory = new ProxyFactory(orderReposirory);
		factory.addAdvisor(getAdvisor(logTrace));
		OrderReposirotyV1 proxy = (OrderReposirotyV1)factory.getProxy();
		log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderReposirory.getClass());
		return proxy;
	}

	@Bean
	public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
		OrderServiceV1Impl orderService = new OrderServiceV1Impl(orderReposirotyV1(logTrace));
		ProxyFactory factory = new ProxyFactory(orderService);
		OrderServiceV1 proxy = (OrderServiceV1)factory.getProxy();
		factory.addAdvisor(getAdvisor(logTrace));
		log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderService.getClass());
		return proxy;
	}

	@Bean
	public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
		OrderControllerV1Impl orderController = new OrderControllerV1Impl(orderServiceV1(logTrace));
		ProxyFactory factory = new ProxyFactory(orderController);
		OrderControllerV1 proxy = (OrderControllerV1)factory.getProxy();
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
