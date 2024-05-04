package com.hashedin.gateway.config;

import java.util.List;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.google.common.net.HttpHeaders;
import com.hashedin.gateway.filter.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	public static class Config {
	}

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
                    // Token is missing
                    return Mono.error(new ResponseStatusException(HttpStatus.SC_UNAUTHORIZED, "Missing authorization header", null));
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                    try {
                        jwtService.validateToken(authHeader);
                        JwtService.TokenInfo tokenInfo = jwtService.extractTokenInfo(authHeader);
                        // Set user ID and roles as headers
                        ServerHttpRequest request = exchange.getRequest().mutate()
                                .header("x-user-id", String.valueOf(tokenInfo.getUserId()))
                                .header("x-user-roles", String.join(",", tokenInfo.getRoles()))
                                .build();
                        exchange = exchange.mutate().request(request).build();
                    } catch (RuntimeException e) {
                        // Token validation failed
                        return Mono.error(new ResponseStatusException(HttpStatus.SC_UNAUTHORIZED, "Invalid or expired token", e));
                    }
                }
            }
            return chain.filter(exchange);
        });
    }

	


}
