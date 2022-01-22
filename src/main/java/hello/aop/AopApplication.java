package hello.aop;

import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.logtrace.ThredaLocalLogTrace;
import hello.proxy.config.v6_aop.AopConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

public class AopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);
	}

}
