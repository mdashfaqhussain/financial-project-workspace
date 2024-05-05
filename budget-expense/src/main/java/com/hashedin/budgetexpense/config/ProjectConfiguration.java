package com.hashedin.budgetexpense.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hashedin.budgetexpense.utility.ResponseUtility;

@Configuration
public class ProjectConfiguration {
	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	ResponseUtility responseUtility() {
		return new ResponseUtility();
	}

}
