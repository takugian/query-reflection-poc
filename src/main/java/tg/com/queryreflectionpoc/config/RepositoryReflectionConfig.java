package tg.com.queryreflectionpoc.config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tg.com.queryreflectionpoc.repository.CadastroRepositoryPoc;

@SuppressWarnings("rawtypes")
@Configuration
public class RepositoryReflectionConfig {

	@Autowired
	private InvocationHandler invocationHandler;

	@Bean
	@Qualifier("feignClient")
	public CadastroRepositoryPoc produceFeignClient() {
		ClassLoader classLoader = CadastroRepositoryPoc.class.getClassLoader();
		Class[] interfaces = new Class[] { CadastroRepositoryPoc.class };
		return (CadastroRepositoryPoc) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
	}
	
}
