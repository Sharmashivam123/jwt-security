package com.epam.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.epam.model.JwtAuthenticationToken;
import com.epam.model.JwtUser;

public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private JwtValidator jwtValidator;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) authentication;
		String token = jwtToken.getToken();
		JwtUser jwtUser = jwtValidator.validate(token);
		if (jwtUser == null)
			throw new RuntimeException("USer doen't exists");
		return new JwtUserDetails(jwtUser.getUsername(), jwtUser.getId(), jwtUser.getRole(), jwtUser.getToken());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
