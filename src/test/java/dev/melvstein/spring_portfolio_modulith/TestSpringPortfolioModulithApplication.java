package dev.melvstein.spring_portfolio_modulith;

import org.springframework.boot.SpringApplication;

public class TestSpringPortfolioModulithApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringPortfolioModulithApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
