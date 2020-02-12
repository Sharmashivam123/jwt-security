package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.model.AuthenticationRequest;
import com.epam.util.JwtTokenGenerator;

@Controller
@ResponseBody
@RequestMapping
public class TokenController {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenGenerator jwtTokenGenerator;

	@PostMapping("/token")
	public String token(@RequestBody AuthenticationRequest authenticationResponse) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationResponse.getUsername(), authenticationResponse.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Username and Password are incorrect.");
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationResponse.getUsername());
		return jwtTokenGenerator.generate(userDetails);
	}

}
