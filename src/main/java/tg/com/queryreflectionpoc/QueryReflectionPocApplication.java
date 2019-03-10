package tg.com.queryreflectionpoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class QueryReflectionPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueryReflectionPocApplication.class, args);
	}

}
