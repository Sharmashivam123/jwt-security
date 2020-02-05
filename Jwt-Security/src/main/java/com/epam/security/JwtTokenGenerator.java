package com.epam.security;

import com.epam.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenGenerator {

	public String generate(JwtUser user) {
		Claims claim = Jwts.claims().setSubject(user.getUsername());
		claim.put("userId", String.valueOf(user.getId()));
		claim.put("role", user.getRole());

	return	Jwts.builder().setClaims(claim).signWith(SignatureAlgorithm.HS256, "youtube").compact();
	}

}
