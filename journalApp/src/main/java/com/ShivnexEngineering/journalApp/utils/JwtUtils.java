package com.ShivnexEngineering.journalApp.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	
	private final Key key;
	private final long EXPIRATION;
	
	public JwtUtils(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.EXPIRATION = expiration;
    }
	
	// Create Token :
	public String generateToken(String userName) {
		return createToken(userName);
	}
	
	public String createToken(String userName) {
		
		return Jwts.builder()
				.setSubject(userName)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
				
	}
	
	// Validates Token :
	public Boolean validateToken(String token, String userName) {
		String extractedUserName = extractUserName(token);
		return (extractedUserName.equals(userName) && !isTokenExpired(token));
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date()); 
	}
	
	public Date extractExpiration(String token) {
		return extractAllClaims(token).getExpiration();
	}
	
	// Extract UserName :
	public String extractUserName(String token) {
		return extractAllClaims(token).getSubject();
	}
	
	
	// Extract PayLoad(Body) from JWT :
	private Claims extractAllClaims(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
}
