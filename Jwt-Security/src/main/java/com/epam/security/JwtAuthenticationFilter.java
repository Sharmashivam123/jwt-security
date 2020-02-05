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
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String header = request.getHeader("Authorisation");
		if (header == null || !header.startsWith("token"))
			throw new RuntimeException("Jwt token is missing.");
		String authToken = header.substring(6);
		JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(authToken);
		return getAuthenticationManager().authenticate(jwtAuthenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}

	public void setSuccessHandler(JwtSuccessHandler jwtSuccessHandler) {
		System.out.println("get the success");
	}

}
