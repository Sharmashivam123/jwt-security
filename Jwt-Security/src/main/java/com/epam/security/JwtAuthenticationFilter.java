package com.epam.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.epam.model.JwtAuthenticationToken;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter() {
		super("/rest/**");
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String header = request.getHeader("Authorisation");
		if (header == null || !header.startsWith("Token "))
			throw new RuntimeException("Jwt Token Missing.");
		String authToken = header.substring(6);
		JwtAuthenticationToken token = new JwtAuthenticationToken(authToken);
		return getAuthenticationManager().authenticate(token);
	}

}
