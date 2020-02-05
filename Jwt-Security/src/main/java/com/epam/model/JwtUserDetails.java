package com.epam.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	String username;
	long id;
	List<GrantedAuthority> list;
	String token;

	public long getId() {
		return id;
	}

	public String getToken() {
		return token;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JwtUserDetails(String username, long id, List<GrantedAuthority> list, String jwtToken) {
		this.username = username;
		this.id = id;
		this.list = list;
		token = jwtToken;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return list;
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

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

}
