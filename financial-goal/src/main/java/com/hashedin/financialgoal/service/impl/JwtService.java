package com.hashedin.financialgoal.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {
	
//	public List<String> extractRolesFromToken(String token) {
//        Claims claims = Jwts.parserBuilder().build().parseClaimsJws(token).getBody();
//        return claims.get("roles", List.class);
//    }
}
