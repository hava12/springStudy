package hello.proxy;

import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.logtrace.ThredaLocalLogTrace;
import hello.proxy.app.v4.BeanPostProcessorConfig;
import hello.proxy.app.v5.autoproxy.AutoProxyConfig;
import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v1_proxy.ConcreteProxyConfig;
import hello.proxy.config.v1_proxy.InterfaceProxyConfig;
import hello.proxy.config.v2_dynamicproxy.handler.DynamicProxtBasicConfig;
import hello.proxy.config.v2_dynamicproxy.handler.DynamicProxtFilterConfig;
import hello.proxy.config.v3_proxyfactory.ProxyFactoryConfigV1;
import hello.proxy.config.v3_proxyfactory.ProxyFactoryConfigV2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

//@Import(AppV1Config.class)
// @Import({AppV1Config.class, AppV2Config.class})
// @Import({InterfaceProxyConfig.class})
// @Import({ConcreteProxyConfig.class})
// @Import({DynamicProxtFilterConfig.class})
// @Import({ProxyFactoryConfigV1.class})
// @Import({ProxyFactoryConfigV2.class})
// @Import({BeanPostProcessorConfig.class})
@Import({AutoProxyConfig.class})
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
