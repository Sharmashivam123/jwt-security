package com.epam.security;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;

public class JwtAuthenticationManager extends ProviderManager {

	public JwtAuthenticationManager(List<AuthenticationProvider> providers) {
		super(providers);
	
	}

}
