package com.hasdedin.user.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.aspectj.weaver.reflect.ArgNameFinder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hasdedin.user.entity.BudgetRole;
import com.hasdedin.user.entity.BudgetUser;

public class CustomUserDetails implements UserDetails{
	
	private String name;
	
	private String password;
	
	private List<GrantedAuthority> authorities;
	
	

	public CustomUserDetails(BudgetUser budgetUser) {
        this.name = budgetUser.getName();
        this.password = budgetUser.getPassword();
        this.authorities = mapRolesToAuthorities(budgetUser.getRole());
    }

    private List<GrantedAuthority> mapRolesToAuthorities(Set<BudgetRole> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
