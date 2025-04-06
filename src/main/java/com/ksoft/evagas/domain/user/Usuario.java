package com.ksoft.evagas.domain.user;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class Usuario implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String login;
	private String senha;
	private UserRole role;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(UserRole.ADMIN.equals(role)) {
			return Arrays.asList(
					new SimpleGrantedAuthority(UserRole.ADMIN.getRole()),
					new SimpleGrantedAuthority(UserRole.USER.getRole())
			);
		}else {
			return Arrays.asList(new SimpleGrantedAuthority(UserRole.USER.getRole()));
		}
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUsername() {
		return login;
	}

}
