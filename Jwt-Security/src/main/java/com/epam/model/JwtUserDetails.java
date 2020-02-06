package com.epam.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String username;
	private Long id;
	private List<GrantedAuthority> role;
	private String token;

	public JwtUserDetails(String username, Long id, List<GrantedAuthority> role, String token) {
		this.username = username;
		this.id = id;
		this.role = role;
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public String getToken() {
		return token;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return role;
	}

	@Override
	public String getPassword() {

		return null;
	}

	@Override
	public String getUsername() {

		return username;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
