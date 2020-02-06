package com.epam.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.epam.model.JwtAuthenticationToken;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	public JwtAuthenticationFilter() {
		super("/rest/**");
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		chain.doFilter(request, response);
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
