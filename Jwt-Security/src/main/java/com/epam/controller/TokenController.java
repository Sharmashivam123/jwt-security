package com.epam.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.model.JwtUser;
import com.epam.security.JwtTokenGenerator;

@RestController
@RequestMapping("/token")
public class TokenController {
	@PostMapping("/{username}")
	public String generate(@RequestBody JwtUser user) {
		JwtTokenGenerator generator = new JwtTokenGenerator();
		return generator.generate(user);
	}
}
