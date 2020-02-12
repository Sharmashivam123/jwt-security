package com.epam.util;

import org.springframework.stereotype.Component;

import com.epam.model.AuthenticationResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenValidator {

	public AuthenticationResponse validate(String token) {
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		try {
			Claims claims = Jwts.parser().setSigningKey("youtube").parseClaimsJwt(token).getBody();
			authenticationResponse.setUsername(claims.getSubject());
			authenticationResponse.setPassword((String) (claims.get("password")));
			authenticationResponse.setRole((String) claims.get("userRole"));
			return null;
		} catch (Exception e) {

		}
		return authenticationResponse;
	}

}
