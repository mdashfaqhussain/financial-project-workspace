package com.hashedin.gateway.filter;

import java.security.Key;
import java.util.List;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

	public void validateToken(final String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public TokenInfo extractTokenInfo(String token) {
		Claims claims = parseToken(token);
		int userId = claims.get("user_id", Integer.class);
		List roles = claims.get("roles", List.class);
		return new TokenInfo(userId, roles);
	}

	private Claims parseToken(String token) {
		JwtParser parser = Jwts.parserBuilder().setSigningKey(getSignKey()).build();
		return parser.parseClaimsJws(token).getBody();
	}

	public static class TokenInfo {
		private int userId;
		private List<String> roles;

		public TokenInfo(int userId, List<String> roles) {
			this.userId = userId;
			this.roles = roles;
		}

		public int getUserId() {
			return userId;
		}

		public List<String> getRoles() {
			return roles;
		}
	}
}
