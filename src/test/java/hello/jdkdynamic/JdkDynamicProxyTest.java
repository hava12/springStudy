package hello.jdkdynamic;

import hello.jdkdynamic.code.AImpl;
import hello.jdkdynamic.code.AInterface;
import hello.jdkdynamic.code.BImpl;
import hello.jdkdynamic.code.BInterface;
import hello.jdkdynamic.code.TimeInvocationHandler;
import java.lang.reflect.Proxy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class JdkDynamicProxyTest {

	@Test
	void dynamicA() {
		AInterface target = new AImpl();

		TimeInvocationHandler handler = new TimeInvocationHandler(target);

		AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[] {AInterface.class}, handler);
		proxy.call();
		log.info("targetClass={}", target.getClass());
		log.info("proxyClass={}", proxy.getClass());
	}
	@Test
	void dynamicB() {
		BInterface target = new BImpl();

		TimeInvocationHandler handler = new TimeInvocationHandler(target);

		BInterface proxy = (BInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[] {BInterface.class}, handler);
		proxy.call();
		log.info("targetClass={}", target.getClass());
		log.info("proxyClass={}", proxy.getClass());
	}
}
