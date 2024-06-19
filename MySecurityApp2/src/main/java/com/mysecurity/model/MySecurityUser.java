package com.mysecurity.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MySecurityUser implements UserDetails {

	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String roles;
	
	public MySecurityUser(String username, String password, String roles) {
		this.username =username;
		this.password =password;
		this.roles =roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		ArrayList<GrantedAuthority> authorities = new ArrayList<>();
		
		SimpleGrantedAuthority role1 = new SimpleGrantedAuthority(roles);
		authorities.add(role1);
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return password;
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
