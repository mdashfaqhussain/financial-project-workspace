package com.hashedin.budgetexpense.config;

import java.util.Collections;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hashedin.budgetexpense.entity.BudgetUser;
import com.hashedin.budgetexpense.repository.IUserRepository;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApplicationInitializer {
	
	@Autowired
	private IUserRepository  userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void init() {
		
		if(!userRepository.existsByName("admin")) {
			BudgetUser adminUser = new BudgetUser();
			adminUser.setName("admin");
			adminUser.setEmail("admin@gmail.com");
			adminUser.setPassword(passwordEncoder.encode("admin"));
			adminUser.setRoles("ADMIN");
			
			userRepository.save(adminUser);
			log.info("Admin user is created");
		}
		
	}

}
