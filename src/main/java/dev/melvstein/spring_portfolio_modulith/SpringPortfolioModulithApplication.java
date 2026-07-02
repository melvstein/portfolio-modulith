package dev.melvstein.spring_portfolio_modulith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringPortfolioModulithApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPortfolioModulithApplication.class, args);
	}

}
