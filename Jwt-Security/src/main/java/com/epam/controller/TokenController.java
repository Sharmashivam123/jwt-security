package com.epam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.model.JwtUser;
import com.epam.security.JwtTokenGenerator;

@Controller
@RequestMapping("/generate")
public class TokenController {
	private JwtTokenGenerator jwtTokenGenerator;

	public TokenController(JwtTokenGenerator jwtTokenGenerator) {
		this.jwtTokenGenerator = jwtTokenGenerator;
	}

	@PostMapping
	public String generate(@RequestBody JwtUser jwtUser) {
		return jwtTokenGenerator.genrate(jwtUser);
	}

}
