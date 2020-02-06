package com.epam.security;

import org.springframework.stereotype.Component;

import com.epam.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {

	JwtUser jwtUser = new JwtUser();

	public JwtUser validate(String token) {
		try {
			Claims claim = Jwts.parser().setSigningKey("youtube").parseClaimsJws(token).getBody();
			jwtUser.setUsername(claim.getSubject());
			jwtUser.setId(Long.parseLong((String) claim.get("userId")));
			jwtUser.setRole((String) claim.get("role"));
		} catch (Exception e) {

		}
		return jwtUser;
	}

}
