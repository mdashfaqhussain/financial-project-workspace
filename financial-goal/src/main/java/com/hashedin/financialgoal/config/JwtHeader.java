package com.hashedin.financialgoal.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

//@Component
//public class JwtHeader extends GenericFilterBean{
//	
////	private static final String USER_ID_HEADER = "x-user-id";
////    private static final String USER_ROLES_HEADER = "x-user-roles";
////
////   
////
////	@Override
////	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
////			throws IOException, ServletException {
////		try {
////			HttpServletRequest httpRequest = (HttpServletRequest) request;
////			String userId = httpRequest.getHeader("x-user-id");
////			String userRoles = httpRequest.getHeader("x-user-roles");
////			UserContext.setUserId(Integer.parseInt(userId));
////            UserContext.setRoles(userRoles);
////            chain.doFilter(request, response);
////		}finally {
////			UserContext.clear();
////		}
////		
////	}
//}
