package com.epam.security;

import org.springframework.stereotype.Component;

import com.epam.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenGenerator {

	public String genrate(JwtUser jwtUser) {
		Claims claim = Jwts.claims().setSubject(jwtUser.getUsername());
		claim.put("userId", jwtUser.getId());
		claim.put("role", jwtUser.getRole());
		return Jwts.builder().setClaims(claim).signWith(SignatureAlgorithm.HS512, "youtube").compact();
	}

}
