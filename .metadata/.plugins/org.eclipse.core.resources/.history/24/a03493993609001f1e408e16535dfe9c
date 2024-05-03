package com.hasdedin.user.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hasdedin.user.entity.BudgetUser;
import com.hasdedin.user.repository.IUserRepository;



@Component
public class CustomUserDetailService implements  UserDetailsService{
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<BudgetUser> userInfo = userRepository.findByName(username);
			return userInfo.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found"));
		
	}

}
