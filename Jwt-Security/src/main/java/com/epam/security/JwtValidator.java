package com.epam.security;

import org.springframework.stereotype.Component;

import com.epam.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {

	public JwtUser validate(String jwtToken) {
		JwtUser jwtUser = new JwtUser();
		try {
			Claims body = Jwts.parser().setSigningKey("youtube").parseClaimsJwt(jwtToken).getBody();
			jwtUser.setUsername(body.getSubject());
			jwtUser.setId(Long.parseLong((String) body.get("userId")));
			jwtUser.setRole((String) body.get("role"));

		} catch (Exception e) {

		}
		return jwtUser;
	}

}
