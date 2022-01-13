package hello.proxy;

import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.logtrace.ThredaLocalLogTrace;
import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v1_proxy.ConcreteProxyConfig;
import hello.proxy.config.v1_proxy.InterfaceProxyConfig;
import hello.proxy.config.v2_dynamicproxy.handler.DynamicProxtBasicConfig;
import hello.proxy.config.v2_dynamicproxy.handler.DynamicProxtFilterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import(AppV1Config.class)
// @Import({AppV1Config.class, AppV2Config.class})
// @Import({InterfaceProxyConfig.class})
// @Import({ConcreteProxyConfig.class})
@Import({DynamicProxtFilterConfig.class})
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
