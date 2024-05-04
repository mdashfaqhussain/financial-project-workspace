package com.hashedin.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import com.google.common.net.HttpHeaders;
import com.hashedin.gateway.filter.JwtService;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>  {
	
	public static class Config{}
	
	@Autowired
	private RouteValidator routeValidator;
	
	@Autowired
	private JwtService jwtService;

	public AuthenticationFilter() {
		super(Config.class);
	}
	
	@Override
	public GatewayFilter apply(Config config) {
	    return ((exchange, chain) -> {
	        if (routeValidator.isSecured.apply(exchange.getRequest())) {
	            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
	                throw new RuntimeException("Missing authorization header");
	            }

	            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
	            if (authHeader != null && authHeader.startsWith("Bearer ")) {
	                authHeader = authHeader.substring(7);
	            }
	            try {
	                jwtService.validateToken(authHeader);
	            } catch (Exception e) {
	                throw new RuntimeException("Unauthorized access to application");
	            }
	        }
	        return chain.filter(exchange);
	    });
	}
	
	

}
