package com.epam.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.epam.model.JwtAuthenticationToken;
import com.epam.model.JwtUser;
import com.epam.model.JwtUserDetails;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

	}

	@Autowired
	JwtValidator validator;

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
		String jwtToken = token.getToken();
		JwtUser jwtUser = validator.validate(jwtToken);
		if (jwtUser == null)
			throw new RuntimeException("jwtToken is incorrect");
		List<GrantedAuthority> list = AuthorityUtils.commaSeparatedStringToAuthorityList(jwtUser.getRole());
		return new JwtUserDetails(jwtUser.getUsername(), jwtUser.getId(), list, jwtToken);
	}

}
