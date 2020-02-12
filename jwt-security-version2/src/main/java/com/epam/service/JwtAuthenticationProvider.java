package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.epam.model.AuthenticationResponse;
import com.epam.model.JwtAuthenticationToken;
import com.epam.model.MyUserDetails;
import com.epam.util.JwtTokenValidator;

@Service
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	JwtTokenValidator jwtTokenValidator;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
		String token = jwtAuthenticationToken.getToken();
		AuthenticationResponse authenticationResponse = jwtTokenValidator.validate(token);
		if (authenticationResponse == null)
			throw new RuntimeException("JwtToken is missing.");
		List<GrantedAuthority> list = AuthorityUtils
				.commaSeparatedStringToAuthorityList(authenticationResponse.getRole());
		return new MyUserDetails(authenticationResponse.getUsername(), authenticationResponse.getPassword(), list);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
