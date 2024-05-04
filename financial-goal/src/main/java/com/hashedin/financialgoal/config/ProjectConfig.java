package com.hashedin.financialgoal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hashedin.financialgoal.utility.ResponseUtility;

@Configuration
public class ProjectConfig {
	
	@Bean
	ResponseUtility responseUtility() {
		return new ResponseUtility();
	}

}
