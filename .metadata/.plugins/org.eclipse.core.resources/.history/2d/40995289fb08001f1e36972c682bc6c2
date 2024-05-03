package com.hasdedin.user.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hasdedin.user.entity.BudgetUser;

public class CustomUserDetails implements UserDetails{
	
	private String name;
    private String password;
    private List<GrantedAuthority> authorities;

    public CustomUserDetails(BudgetUser userInfo) {
        name = userInfo.getName();
        password = userInfo.getPassword();
        authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
