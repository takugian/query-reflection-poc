package tg.com.queryreflectionpoc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tg.com.queryreflectionpoc.core.reflection.InvocationHandlerImpl;

@Configuration
public class InvocationHandlerConfig {

	@Bean
	public InvocationHandlerImpl produce() {
		return new InvocationHandlerImpl();
	}

}
