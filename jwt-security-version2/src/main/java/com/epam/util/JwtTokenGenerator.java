package com.epam.util;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenGenerator {

	public String generate(UserDetails userDetails) {
		Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
		claims.put("userRole", userDetails.getAuthorities());
		claims.put("password", userDetails.getPassword());
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, "youtube").compact();
	}

}
