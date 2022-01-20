package hello.proxy;

import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.logtrace.ThredaLocalLogTrace;
import hello.proxy.config.v6_aop.AopConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import(AppV1Config.class)
// @Import({AppV1Config.class, AppV2Config.class})
// @Import({InterfaceProxyConfig.class})
// @Import({ConcreteProxyConfig.class})
// @Import({DynamicProxtFilterConfig.class})
// @Import({ProxyFactoryConfigV1.class})
// @Import({ProxyFactoryConfigV2.class})
// @Import({BeanPostProcessorConfig.class})
// @Import({AutoProxyConfig.class})
@Import({AopConfig.class})
@SpringBootApplication(scanBasePackages = "hello.proxy.app")
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	@Bean
	public LogTrace logTrace() {
		return new ThredaLocalLogTrace();
	}
}
