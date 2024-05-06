package com.hashedin.gateway.config;

import java.util.List;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;

@Component
public class RouteValidator {
	
//	public static final List<String> openApiEndpoints= List.of(
//				"/user/register",
//				"/user/login",
//				"/eureka"
//			);
//	
//	public Predicate<ServerHttpRequest> isSecured = request ->
//    	openApiEndpoints.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));
	
	public Predicate<ServerHttpRequest> isSecured = request -> false;
	
}
