package com.hashedin.financialgoal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;



@SpringBootApplication
@EnableDiscoveryClient
public class FinancialGoalApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialGoalApplication.class, args);
	}

}
